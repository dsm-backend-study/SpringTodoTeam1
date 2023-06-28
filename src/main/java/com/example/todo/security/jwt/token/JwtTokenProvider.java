package com.example.todo.security.jwt.token;

import com.example.todo.TodoCodeBoomberApplication;
import com.example.todo.domain.user.repository.UserRepository;
import com.example.todo.security.jwt.dto.TokenResponse;
import com.example.todo.security.jwt.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final long accessExpiration = 1800000L; // 60 * 30 * 1000L  30 minute

    private final long refreshExpiration = 1080000L; // 60 * 180 * 1000L  3 hour

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    public TokenResponse createToken(String userId) {

        Date now = new Date();

        String accessToken = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessExpiration))
                .signWith(TodoCodeBoomberApplication.key)
                .compact();

        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshExpiration))
                .signWith(TodoCodeBoomberApplication.key)
                .compact();

        return TokenResponse.builder()
                .type("jwt")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenResponse reIssue(String refreshToken) {

        RefreshToken foundRefreshToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(); // invalid refresh token exception

        userRepository.findByUserId(foundRefreshToken.getUserId())
                .orElseThrow(); // invalid refresh token exception

        refreshTokenRepository.delete(foundRefreshToken);

        return createToken(foundRefreshToken.getUserId());
    }

    public Authentication getAuthentication(String jwtToken) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(TodoCodeBoomberApplication.key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("Authorization").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String validateToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(TodoCodeBoomberApplication.key).build().parseClaimsJws(token).getBody();
        if (claims.getSubject() == null) throw new NullPointerException();
        var now = new Date();
        if (now.after(new Date(now.getTime() + claims.getExpiration().getTime()))) throw new NullPointerException();
        return claims.getSubject();
    }
}
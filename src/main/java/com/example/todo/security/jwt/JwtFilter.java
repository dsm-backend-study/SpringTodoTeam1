package com.example.todo.security.jwt;

import com.example.todo.security.auth.CustomUserDetailsService;
import com.example.todo.security.jwt.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//
//        String jwtToken = resolveToken(httpServletRequest);
//
//        String requestUri = httpServletRequest.getRequestURI();
//
//        if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
//
//            Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }

        String token = resolveToken(servletRequest);
        if(token != null) {
            String subject = jwtTokenProvider.validateToken(token);
            var userDetails = customUserDetailsService.loadUserByUsername(subject);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, subject, userDetails.getAuthorities()));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

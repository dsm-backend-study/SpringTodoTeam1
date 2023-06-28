package com.example.todo.security.jwt.token;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class RefreshToken {

    @Id
    private String userId;

    private String token;
}

package com.example.todo;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;
import java.util.Base64;

@SpringBootApplication
public class TodoCodeBoomberApplication {

    public static Key key;

    public static void main(String[] args) {
        byte[] keyBytes = Decoders.BASE64.decode(Base64.getEncoder().encodeToString("wow@its!so*cute#project%and!im*cute#$too".getBytes()));
        key = Keys.hmacShaKeyFor(keyBytes);
        SpringApplication.run(TodoCodeBoomberApplication.class, args);
    }

}

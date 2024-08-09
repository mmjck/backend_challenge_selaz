package com.selaz.ms.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TestUtils {
    private static String secret = "zZrq0sZK1yt9RJk51RTJ/jeU6WERbvr8nqKMWQJRX1E=";

    public static String generateToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String token = JWT.create()
                .withIssuer("auth-service")
                .withExpiresAt(generateExpirationDate())
                .withSubject(username)
                .sign(algorithm);

        return token;
    }

    public static String object2Json(Object ob) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(ob);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

package com.br.airsystem.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.airsystem.model.User;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public abstract class DefaultController {

    private final String SECRET = "dW1hIGZyYXNlIGFp";
    private final String TOKEN_PREFIX = "Bearer ";
    private final String ISSUER = "AirSystem";

    protected final String generateToken(long userId) {
        return TOKEN_PREFIX + JWT.create().withIssuer(ISSUER).withIssuedAt(new Date())
                .withExpiresAt(DateUtils.addHours(new Date(), 2))
                .withClaim("userId", userId)
                .sign(Algorithm.HMAC512(SECRET));
    }

    protected final User getLoggedUser(String token) {
        Algorithm algorithm = Algorithm.HMAC512(SECRET);
        DecodedJWT jwt = JWT.require(algorithm).withIssuer(ISSUER).build().verify(token.replace(TOKEN_PREFIX, ""));
        return User.builder().id(jwt.getClaim("userId").asLong()).build();
    }
}

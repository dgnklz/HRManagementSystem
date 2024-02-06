package com.dgnklz.hrmanagementsystem.core.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class TokenManager {
    @Value("${auth-service.secret.key}")
    String secretKey;

    @Value("${auth-service.issuer}")
    String issuer;

    @Value("${auth-service.expire.date}")
    Long expireDate;

    public Optional<String> createToken(Long id) {
        String token = "";
        try {
            token = JWT.create().withAudience()
                    .withClaim("id", id)
                    .withClaim("serviceName", "AuthService")
                    .withClaim("lastJoin", System.currentTimeMillis())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireDate))
                    .sign(Algorithm.HMAC256(secretKey));
            return Optional.of(token);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Long> getIdInfoFromToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT == null)
                return Optional.empty();

            Long id = decodedJWT.getClaim("id").asLong();

            String serviceName = decodedJWT.getClaim("serviceName").asString();

            return Optional.of(id);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT == null)
                return false;

        } catch (Exception e) {
            return false;
        }
        return true;
    }


}

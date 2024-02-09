package com.dgnklz.hrmanagementsystem.services.conceretes;

import com.dgnklz.hrmanagementsystem.cores.exceptions.types.TokenException;
import com.dgnklz.hrmanagementsystem.models.securities.UserDetailsImpl;
import com.dgnklz.hrmanagementsystem.services.abstracts.TokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManager implements TokenService {
    @Value("${auth-service.secret.key}")
    private String secretKey;

    @Value("${auth-service.expire.time}")
    private int expirationTime;

    @Override
    public String generateToken(Authentication auth) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) auth.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                //.setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuedAt(new Date())
                //.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setExpiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new TokenException("Invalid Token");
        } catch (ExpiredJwtException e) {
            throw new TokenException("Token is expired");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("Token is unsupported");
        } catch (IllegalArgumentException e) {
            throw new TokenException("Token claims string is empty");
        }
    }

    private Key key() {
        //return Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
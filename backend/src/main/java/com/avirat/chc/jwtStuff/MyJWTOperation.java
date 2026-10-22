package com.avirat.chc.jwtStuff;


import com.avirat.chc.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class MyJWTOperation {

    public static final long JWT_TOKEN_VALIDITY = 5*60*60;
    private String secret = "abcdefghijklmnopqrstuvwxyzhfdfhjhjgyfykjkejjejnjnjkdfffffffdddddddddddddddddddddddddddd";

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof UserEntity u) {
            claims.put("role", u.getRole());
            claims.put("userId", u.getUserId());
            claims.put("healthCardNo", u.getHealthCardNo());
        }
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }


    //retrive username from jwt token
    public  String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        //Starts building a JWT parser -> This is required to decode or validate a token
        return Jwts.parser()
                //Sets the secret key used to verify the token’s signature
                .setSigningKey(secret).build()
               //Verifies the signature ->Decodes the payload (claims)
                .parseClaimsJws(token)
              // Extracts just the Claims part (i.e., the body), which contains all the data stored in the token
                .getBody();
    }



    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);// ->(Claims){return Date}
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
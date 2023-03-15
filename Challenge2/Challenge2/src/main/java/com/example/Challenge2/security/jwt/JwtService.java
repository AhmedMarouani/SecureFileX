package com.example.Challenge2.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String secretKey = "NdRgUkXp2s5v8y/B?E(H+KbPeShVmYq3\n";


    // The Claims::getSubject method reference is a Function that extracts the subject
    // (i.e., user email) of the JWT token.
    public String extractUserEmail(String token) {

        return extractClaim(token, Claims::getSubject);
    }


    //This method takes in a JWT token as a String and a Function that extracts a specific claim,
    // then applies the claimsResolver function to extract the specific claim.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    //generate token using extra claims and user details in case we needed details other than the username(email)
    //used to add token dates and expiration dates
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    //to validate if the token belongs to the userDetails
    Boolean isTokenValid(String token, UserDetails userDetails){
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //returns all claims of the token as a Claims object by extracting the token's body  using Jwts builder
    private Claims extractAllClaims(String token){
        return Jwts.
        parserBuilder().
        setSigningKey(getSigningKey()).// used to verify the token's signature.
        build().
        parseClaimsJws(token).
        getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

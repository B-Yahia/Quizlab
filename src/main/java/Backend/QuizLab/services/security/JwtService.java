package Backend.QuizLab.services.security;

import Backend.QuizLab.dtos.user.TokenPair;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String jwtSecretKey;
    @Value("${app.jwt.expiration}")
    private Long jwtExpirationInMS;
    @Value("${app.jwt.refresh_expiration}")
    private Long jwtRefreshExpiration;
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    // Method to generate the access token
    public String generateAccessToken (Authentication authentication){
        return generateToken(authentication, jwtExpirationInMS , new HashMap<>());
    }

    // Method to generate the refresh token
    public String generateRefreshToken (Authentication authentication){
        Map<String,String> claims = new HashMap<>();
        claims.put("tokenType","refresh");
        return generateToken(authentication, jwtRefreshExpiration , claims);
    }

    // Method to validate Token
    public boolean validateUserToken(String token , UserDetails userDetails){
        final String username = extractUserEmailFromToken(token);
        return username != null && username.equals(userDetails.getUsername()) && !tokenBlacklistService.isTokenBlacklisted(token);
    }

    public boolean isValidToken(String token){
        Claims claims = extractAllClaims(token);
        return claims != null && !tokenBlacklistService.isTokenBlacklisted(token);
    }

    // Check if it is a refresh token
    public boolean isRefreshToken (String token){
        Claims claims = extractAllClaims(token);
        if (claims==null) return false;
        return  "refresh".equals(claims.get("tokenType"));
    }


    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateToken(Authentication authentication, Long expirationTime, Map<String,String> claims) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date epirationDate = new Date(now.getTime()+expirationTime);
        return Jwts.builder()
                .header()
                .add("typ","JWT")
                .and()
                .subject(userPrincipal.getUsername())
                .issuedAt(now)
                .claims(claims)
                .expiration(epirationDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUserEmailFromToken(String token) {
        Claims claims = extractAllClaims(token);
        if (claims!= null){
            return claims.getSubject();
        }
        return  null;
    }


    public TokenPair generateTokenPair(Authentication authentication) {
        String accessToken = generateAccessToken(authentication);
        String refreshToken = generateRefreshToken(authentication);
        return new TokenPair(accessToken, refreshToken);
    }

    public Date extractExpiration(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    private Claims extractAllClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
        return claims;
    }
}

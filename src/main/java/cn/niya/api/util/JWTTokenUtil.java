package cn.niya.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * got String token and some token setting
 */

@Slf4j
public class JWTTokenUtil {


    private static final long tokenExpiration = 24 * 60 * 60 * 1000;

    private static final String tokenSignKey = "i m niya";

    private static final String userRoleKey = "userRole";


    public static String createToken(String userName) {
        return Jwts.builder().setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512 , tokenSignKey).compact();
    }

    public static String createToken(String userName, String role) {
        return Jwts.builder().setSubject(userName)
                .claim(userRoleKey, role)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compact();
    }

    public static String getUserNameFromToken(String token) {
        String userName = null;
        try {
            userName = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return userName;
    }

    public static String getUserRoleFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody();
            return claims.get(userRoleKey).toString();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


}

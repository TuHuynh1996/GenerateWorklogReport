package gwr.library.security.ultis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import gwr.library.security.UserPrincipal;

/**
 * The Class JwtTokenUtil.
 */
@Service
public class JwtTokenUtil {
    
    /** The secret key. */
    @Value("${jwtproperties.secret}")
    private String secretKey;
    
    /**
     * Generate token.
     *
     * @param userDetail the user detail
     * @return the string
     */
    public String generateToken(UserPrincipal userDetail) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetail.getUsername());
    }
    
    public String getUserName(String token) {
        return JWT.require(Algorithm.HMAC512(secretKey.getBytes())).build().verify(token).getSubject();
    }
    
//    public Boolean validateToken(String token , UserPrincipal userDetail) {
//        String userName = extractUserName(token);
//    }
//    
//
//    private <T> T  extractClaim(String token, Function<Claim, T> claimsResolver) {
//        return JWT.decode(token).getClaim();
//    }

    /**
     * Creates the token.
     *
     * @param claims the claims
     * @param username the username
     * @return the string
     */
    private String createToken(Map<String, Object> claims, String username) {
        return JWT.create().withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }
    
    
}

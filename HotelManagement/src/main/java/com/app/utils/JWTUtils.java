package com.app.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Service
public class JWTUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

	  @Value("${SECRET_KEY}")
	  private String jwtSecret;

	
	  @Value("${EXP_TIMEOUT}")
	  private int jwtExpirationMs;

	  public String generateJwtToken(UserDetails userPrincipal) {
	    return generateTokenFromUsername(userPrincipal.getUsername());
	  }

	  public String generateTokenFromUsername(String username) {
	    return Jwts.builder().setSubject(username).setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
	        .compact();
	  }

	  public String getUserNameFromJwtToken(String token) {
		  
	    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	    
	  }

	  public boolean validateToken(String token)
	  {
		  if (token == null || jwtSecret == null) {
		        logger.error("authToken or jwtSecret is null");
		        return false;
		    }
		  
		  try {
		        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		        
		        return true;
		        
		    } catch(SignatureException e) {
		        logger.error("Invalid JWT signature: {}", e.getMessage());
		    } catch(MalformedJwtException e) {
		        logger.error("Invalid JWT token: {}", e.getMessage());
		    } catch(ExpiredJwtException e) {
		        logger.error("JWT token is expired: {}", e.getMessage());
		    } catch(UnsupportedJwtException e) {
		        logger.error("JWT token is unsupported: {}", e.getMessage());
		    } catch(IllegalArgumentException e) {
		        logger.error("JWT claims string is empty: {}", e.getMessage());
		    }
		  return false;
	  }
	
}

	

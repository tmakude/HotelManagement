package com.app.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.springframework.stereotype.Service;

@Service
public class JWTUtils {
	
	private static final long EXPIRATION_TIME =1000*60*24*7;
	
	private final SecretKey key;
	
	public JWTUtils() {
		
		String secreteString= "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
		byte[] keyBytes=Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
		this.key = new SecretKeySpec(keyBytes, "HMACSHA_256");
		
	}
	
	

}

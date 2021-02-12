package org.capestart.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.capestart.dto.LMSResponseDTO;
import org.capestart.entity.User;
import org.capestart.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {
	
	private static Logger logger = LogManager.getLogger(AdminService.class);
	
	private static final int JWT_VALIDITY = 24 * 60 * 60 * 1000;
	
	@Value("${lms.jwt.secret}")
	private String JWT_SECRET;
	
	@Autowired
	UserRepo userRepo;
	
	public ResponseEntity<LMSResponseDTO> validateLogin(User user){
		try {
			User dbUser = userRepo.findUserbyName(user.getName());
			if(dbUser==null || !dbUser.getPassword().equals(user.getPassword()))
				return new ResponseEntity<>(new LMSResponseDTO("UserName/Password is wrong"), HttpStatus.BAD_REQUEST);
			String token = generateToken(dbUser.getId(),dbUser.getIsAdmin()=='Y'?true:false);
			if(token==null)
				return new ResponseEntity<>(new LMSResponseDTO("Something went wrong. Kindly try again"), HttpStatus.INTERNAL_SERVER_ERROR);
			HashMap<String, Object> map  =new HashMap<>();
			map.put("admin", dbUser.getIsAdmin());
			map.put("token", token);
			map.put("name", dbUser.getName());
			return ResponseEntity.ok(new LMSResponseDTO(map));
		} catch (Exception e) {
			logger.error("Exception while validating user : Exception - {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Something went wrong. Kindly Try again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> validateToken(String token){
		try {
			Claims claims = decodeToken(token);
			return ResponseEntity.ok(new LMSResponseDTO((boolean)claims.get("ADMIN")==true?"ADMIN":"USER",1));
		} catch (Exception e) {
			logger.error("Exception while validating Token : Exception - {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Something went wrong. Kindly Try again"), HttpStatus.UNAUTHORIZED);
	}
	
	private String generateToken(Integer id,boolean isAdmin) {
		try {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
		    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		    JwtBuilder builder = Jwts.builder()
		    		.setId(UUID.randomUUID().toString())
		    		.claim("ID", id)
		    		.claim("ADMIN", isAdmin)
		            .signWith(SignatureAlgorithm.HS256, signingKey)
		            .setExpiration(new Date(System.currentTimeMillis()+JWT_VALIDITY));
			return builder.compact();
		} catch (Exception e) {
			logger.error("Error while generating JWT Token :: Exception {} ",e.getMessage());
		}
		return null;
	}
	
	public Claims decodeToken(String token) {
		return Jwts.parser()
		       .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET))
		       .parseClaimsJws(token).getBody();
	}

}

package org.capestart.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.capestart.dto.LMSResponseDTO;
import org.capestart.entity.User;
import org.capestart.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class LoginController {
	
	@Value("${lms.image.path}")
	private String BASE_IMAGE_PATH;
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<LMSResponseDTO> validateLogin(@RequestBody User user){
		return loginService.validateLogin(user);
	}
	
	@GetMapping("/token")
	public ResponseEntity<LMSResponseDTO> validateToken(@RequestHeader(value = "Authorization", required = true) String token){
		return loginService.validateToken(token);
	}
	
	@GetMapping("/image/{id}")
	public void getImage(@PathVariable String id,HttpServletResponse response) {
		try(InputStream in = new FileInputStream(new File(BASE_IMAGE_PATH+id))) {
		    IOUtils.copy(in, response.getOutputStream());
		} catch (Exception e) {
		}
	}

}

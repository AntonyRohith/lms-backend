package org.capestart.controller;

import org.capestart.dto.LMSResponseDTO;
import org.capestart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/get-available-books")
	public ResponseEntity<LMSResponseDTO> getAvailableBooks(){
		return userService.getAvailableBooks();
	}
	
	@GetMapping("/search-books")
	public ResponseEntity<LMSResponseDTO> searchBooks(@RequestParam(value = "keyword",required = true)String keyword){
		return userService.searchBooks(keyword);
	}
	
	@PostMapping("/lend-book")
	public ResponseEntity<LMSResponseDTO> getAllAuthors(@RequestAttribute(value = "ID") Integer userId,@RequestParam(value="bookId")Integer bookId){
		return userService.lendBook(userId,bookId);
	}

}

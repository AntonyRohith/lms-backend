package org.capestart.controller;

import org.capestart.dto.LMSResponseDTO;
import org.capestart.entity.Author;
import org.capestart.entity.Book;
import org.capestart.entity.Publisher;
import org.capestart.entity.User;
import org.capestart.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/update-book-image")
	public ResponseEntity<LMSResponseDTO> updateBookImage(@RequestParam("file") MultipartFile file,@RequestParam("id")Integer id){
		return adminService.updateBookImage(file,id);
	}
	
	@PutMapping("/create-author")
	public ResponseEntity<LMSResponseDTO> createAuthor(@RequestBody Author author){
		return adminService.createAuthor(author);
	}
	
	@PutMapping("/create-publisher")
	public ResponseEntity<LMSResponseDTO> createPublisher(@RequestBody Publisher publisher){
		return adminService.createPublisher(publisher);
	}
	
	@PutMapping("/create-user")
	public ResponseEntity<LMSResponseDTO> createUser(@RequestBody User user){
		return adminService.createUser(user);
	}
	
	@PutMapping("/create-book")
	public ResponseEntity<LMSResponseDTO> createBook(@RequestBody Book book){
		return adminService.createBook(book);
	}
	
	@GetMapping("/get-all-authors")
	public ResponseEntity<LMSResponseDTO> getAllAuthors(){
		return adminService.getAuthors();
	}
	
	@GetMapping("/get-all-publishers")
	public ResponseEntity<LMSResponseDTO> getAllPublishers(){
		return adminService.getPublishers();
	}
	
	@GetMapping("/get-all-books")
	public ResponseEntity<LMSResponseDTO> getAllbooks(){
		return adminService.getBooks();
	}
	
	@GetMapping("/get-all-users")
	public ResponseEntity<LMSResponseDTO> getAllUsers(){
		return adminService.getUsers();
	}
	
	@PostMapping("/update-author")
	public ResponseEntity<LMSResponseDTO> updateAuthor(@RequestBody Author author){
		return adminService.updateAuthor(author);
	}
	
	@PostMapping("/update-publisher")
	public ResponseEntity<LMSResponseDTO> updatePublisher(@RequestBody Publisher publisher){
		return adminService.updatePublisher(publisher);
	}
	
	@PostMapping("/update-user")
	public ResponseEntity<LMSResponseDTO> updateUser(@RequestBody User user){
		return adminService.updateUser(user);
	}
	
	@PostMapping("/update-book")
	public ResponseEntity<LMSResponseDTO> updateBook(@RequestBody Book book){
		return adminService.updateBook(book);
	}
	
	@DeleteMapping("/delete-author")
	public ResponseEntity<LMSResponseDTO> deleteAuthor(@RequestParam(value = "id") Integer id){
		return adminService.deleteAuthor(id);
	}
	
	@DeleteMapping("/delete-publisher")
	public ResponseEntity<LMSResponseDTO> deletePublisher(@RequestParam(value = "id") Integer id){
		return adminService.deletePublisher(id);
	}
	
	@DeleteMapping("/delete-user")
	public ResponseEntity<LMSResponseDTO> deleteUser(@RequestParam(value = "id") Integer id){
		return adminService.deleteUser(id);
	}
	
	@DeleteMapping("/delete-book")
	public ResponseEntity<LMSResponseDTO> deleteBook(@RequestParam(value = "id") Integer id){
		return adminService.deleteBook(id);
	}

}

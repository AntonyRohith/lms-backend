package org.capestart.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.capestart.dto.LMSResponseDTO;
import org.capestart.entity.Author;
import org.capestart.entity.Book;
import org.capestart.entity.Publisher;
import org.capestart.entity.User;
import org.capestart.repository.AuthorRepo;
import org.capestart.repository.BookRepo;
import org.capestart.repository.PublisherRepo;
import org.capestart.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminService {
	
	private static Logger logger = LogManager.getLogger(AdminService.class);
	
	@Value("${lms.image.path}")
	private String BASE_IMAGE_PATH;
	
	@Autowired
	AuthorRepo authRepo;
	
	@Autowired
	PublisherRepo pubRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BookRepo bookRepo;
	
	public ResponseEntity<LMSResponseDTO> updateBookImage(MultipartFile file,Integer id){
		try {
			int update = bookRepo.updateBookImage(id, id+file.getOriginalFilename());
			if(update==1) {
				Files.copy(file.getInputStream(), Paths.get(BASE_IMAGE_PATH, id+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
				return ResponseEntity.ok(new LMSResponseDTO("Book Image updated Sucessfully"));
			}
		} catch (Exception e) {
			logger.error("Error while update Book Image. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> createAuthor(Author author){
		try {
			author.setActive('Y');
			authRepo.save(author);
			return ResponseEntity.ok(new LMSResponseDTO("Author Created Sucessfully"));
		} catch (Exception e) {
			logger.error("Error while creating Author. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> createPublisher(Publisher publisher){
		try {
			publisher.setActive('Y');
			pubRepo.save(publisher);
			return ResponseEntity.ok(new LMSResponseDTO("Publisher Created Sucessfully"));
		} catch (Exception e) {
			logger.error("Error while creating Publisher. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> createUser(User user){
		try {
			user.setActive('Y');
			userRepo.save(user);
			return ResponseEntity.ok(new LMSResponseDTO("User Created Sucessfully"));
		} catch (Exception e) {
			logger.error("Error while creating User. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> createBook(Book book){
		try {
			book.setActive('Y');
			book.setIsAvailable('Y');
			book.setImg("default.jpg");
			bookRepo.save(book);
			return ResponseEntity.ok(new LMSResponseDTO("Book Created Sucessfully"));
		} catch (Exception e) {
			logger.error("Error while creating Book. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> getAuthors(){
		try {
			List<Author> list = (List<Author>) authRepo.findAll();
			return ResponseEntity.ok(new LMSResponseDTO(list));
		} catch (Exception e) {
			logger.error("Error while reading Author. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public ResponseEntity<LMSResponseDTO> getPublishers(){
		try {
			List<Publisher> list = (List<Publisher>) pubRepo.findAll();
			return ResponseEntity.ok(new LMSResponseDTO(list));
		} catch (Exception e) {
			logger.error("Error while reading Publisher. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> getUsers(){
		try {
			List<User> list = (List<User>) userRepo.findAll();
			return ResponseEntity.ok(new LMSResponseDTO(list));
		} catch (Exception e) {
			logger.error("Error while reading User. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> getBooks(){
		try {
			List<Book> list = (List<Book>) bookRepo.findAll();
			return ResponseEntity.ok(new LMSResponseDTO(list));
		} catch (Exception e) {
			logger.error("Error while reading Book. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> updateAuthor(Author author){
		try {
			authRepo.updateNameActive(author.getId(), author.getName(), author.getActive());
			return ResponseEntity.ok(new LMSResponseDTO("Author updated Successfully"));
		} catch (Exception e) {
			logger.error("Error while updating Author. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> updatePublisher(Publisher publisher){
		try {
			pubRepo.updateNameActive(publisher.getId(), publisher.getName(), publisher.getActive());
			return ResponseEntity.ok(new LMSResponseDTO("Publisher updated Successfully"));
		} catch (Exception e) {
			logger.error("Error while updating Publisher. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> updateUser(User user){
		try {
			userRepo.updateNameActive(user.getId(), user.getName(), user.getActive());
			return ResponseEntity.ok(new LMSResponseDTO("User updated Successfully"));
		} catch (Exception e) {
			logger.error("Error while updating User. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> updateBook(Book book){
		try {
			bookRepo.updateNameActive(book.getId(), book.getName(), book.getActive());
			return ResponseEntity.ok(new LMSResponseDTO("Book updated Successfully"));
		} catch (Exception e) {
			logger.error("Error while updating Book. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> deleteAuthor(Integer id){
		try {
			bookRepo.deleteBooksByAuthor(id);
			authRepo.deleteById(id);
			return ResponseEntity.ok(new LMSResponseDTO("Author and related books deleted Successfully"));
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(new LMSResponseDTO("Some Books of this Author is lended by users. So unable to delete Author"), HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error("Error while deleting Author. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> deletePublisher(Integer id){
		try {
			bookRepo.deleteBooksByPublisher(id);
			pubRepo.deleteById(id);
			return ResponseEntity.ok(new LMSResponseDTO("Publisher and related books deleted Successfully"));
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(new LMSResponseDTO("Some Books of this publisher is lended by users. So unable to delete publisher"), HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error("Error while deleting Publisher. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> deleteUser(Integer id){
		try {
			userRepo.deleteById(id);
			return ResponseEntity.ok(new LMSResponseDTO("User Deleted Successfully"));
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(new LMSResponseDTO("Some Books are lended by this users. So unable to delete User"), HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error("Error while deleting User. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> deleteBook(Integer id){
		try {
			int isDeleted = bookRepo.deleteBook(id);
			if(isDeleted==1)
				return ResponseEntity.ok(new LMSResponseDTO("Book Deleted Successfully"));
			return new ResponseEntity<>(new LMSResponseDTO("Book is lended by a user. So unable to delete Book"), HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error("Error while deleting Book. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

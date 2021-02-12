package org.capestart.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.capestart.dto.BookDTO;
import org.capestart.dto.LMSResponseDTO;
import org.capestart.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private static Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	BookRepo bookRepo;
	
	public ResponseEntity<LMSResponseDTO> getAvailableBooks(){
		try {
			List<Object[]> list = bookRepo.findAvailableBooks();
			List<BookDTO> dto = new ArrayList<>();
			list.forEach(e->{
				BookDTO temp = new BookDTO();
				temp.setId((int)e[0]);
				temp.setBook(e[1].toString());
				temp.setImg((String) Optional.ofNullable(e[2]).orElse(""));
				temp.setAvailable((char)e[3]);
				temp.setTime((String) Optional.ofNullable(e[4]).orElse(""));
				temp.setAuthor(e[5].toString());
				temp.setPublisher(e[6].toString());
				dto.add(temp);
			});
			return ResponseEntity.ok(new LMSResponseDTO(dto));
		} catch (Exception e) {
			logger.error("Error while reading  available Book. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Error while reading  available Book"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> searchBooks(String keyword){
		try {
			List<Object[]> list = bookRepo.searchBooks(keyword);
			List<BookDTO> dto = new ArrayList<>();
			list.forEach(e->{
				BookDTO temp = new BookDTO();
				temp.setId((int)e[0]);
				temp.setBook(e[1].toString());
				temp.setImg(e[2].toString());
				temp.setAvailable((char)e[3]);
				temp.setTime(e[4].toString());
				temp.setAuthor(e[5].toString());
				temp.setPublisher(e[6].toString());
				dto.add(temp);
			});
			return ResponseEntity.ok(new LMSResponseDTO(dto));
		} catch (Exception e) {
			logger.error("Error while reading  available Book. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Error while reading  available Book"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<LMSResponseDTO> lendBook(Integer userId,Integer bookId){
		try {
			LocalDate date = LocalDate.now().plusDays(4);
			int isLended = bookRepo.lendBook(userId,bookId,date.toString());
			if(isLended==1)
				return ResponseEntity.ok(new LMSResponseDTO("Book Lended Successfully"));
			return new ResponseEntity<>(new LMSResponseDTO("Book is lended by other user. So unable to lend Book"), HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error("Error while lending Book. Exception {}",e.getMessage());
		}
		return new ResponseEntity<>(new LMSResponseDTO("Error while lending Book"),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

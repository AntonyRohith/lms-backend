package org.capestart.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.capestart.entity.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface BookRepo extends BaseRepository<Book> {
	
	@Query("select b.id,b.name,b.img,b.isAvailable,b.availableTime,a.name,p.name from Book b inner join b.authorDetail a inner join b.publisherDetail p where b.active='Y' and a.active='Y' and p.active='Y' ")
	List<Object[]> findAvailableBooks();
	
	@Query("select b.id,b.name,b.img,b.isAvailable,b.availableTime,a.name,p.name from Book b inner join b.authorDetail a inner join b.publisherDetail p where b.active='Y' and a.active='Y' and p.active='Y' and (a.name= ?1 or b.name= ?1 or p.name= ?1) ")
	List<Object[]> searchBooks(String keyword);
	
	@Transactional
	@Modifying
	@Query("delete from Book where author= ?1 and isAvailable='Y'")
	void deleteBooksByAuthor(Integer id);
	
	@Transactional
	@Modifying
	@Query("delete from Book where publisher= ?1 and isAvailable='Y'")
	void deleteBooksByPublisher(Integer id);
	
	@Transactional
	@Modifying
	@Query("delete from Book where id=?1 and isAvailable='Y'")
	int deleteBook(Integer id);
	
	@Transactional
	@Modifying
	@Query("update Book set isAvailable='N', availableTime= ?3, user=?1 where id= ?2 and isAvailable='Y'")
	int lendBook(Integer userId,Integer bookId,String time);
	
	@Transactional
	@Modifying
	@Query("update Book set img=?2  where id= ?1")
	int updateBookImage(Integer id,String path);

}

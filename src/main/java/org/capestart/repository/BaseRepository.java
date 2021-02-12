package org.capestart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Integer> {
	
	@Query("select t from #{#entityName} t ")
	List<T> findAll();
	
	@Transactional
	@Modifying
	@Query("update #{#entityName} set name=?2, active=?3 where id= ?1")
	void updateNameActive(Integer id,String name, Character active);

}

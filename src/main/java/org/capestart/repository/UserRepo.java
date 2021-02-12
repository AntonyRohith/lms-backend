package org.capestart.repository;

import javax.transaction.Transactional;

import org.capestart.entity.User;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface UserRepo extends BaseRepository<User> {
	
	@Query("select u from User u where u.name =?1 and u.active='Y'")
	User findUserbyName(String name);

}

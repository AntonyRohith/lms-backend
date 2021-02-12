package org.capestart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.capestart.entity.User;
import org.capestart.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryManagementSystemApplicationTests {
	
	@Autowired
	UserRepo userRepo;

	@Test
	void connectionTesting() {
		User generic = new User();
		generic.setName("Admin");
		generic.setPassword("Password");
		generic.setIsAdmin('Y');
		generic.setActive('Y');
		User found = userRepo.save(generic);
		assertNotNull(found);
        assertEquals(generic.getName(), found.getName());
	}

}

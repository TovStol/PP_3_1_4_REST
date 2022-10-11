package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.Collection;
import java.util.HashSet;

@SpringBootTest
class SpringBootSecurityDemoApplicationTests {

	@Test
	void contextLoads() {
	}

//	void createUser(){
//		User user1 = new User("Alex", "Borisov", (byte) 39);
//		Role role1 = new Role("ADMIN");
//		Role role2 = new Role("USER");
//		Collection<Role> role = null;
//		role.add(role1);
//		role.add(role2);
//		user1.setRole(role);
//		userService.saveUser(user1);
//
//	}
}

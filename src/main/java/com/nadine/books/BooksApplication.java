package com.nadine.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nadine.books.genres.Role;
import com.nadine.books.genres.User;
import com.nadine.books.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class BooksApplication implements CommandLineRunner {
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@PostConstruct
	void init_users() {
		ensureRoleExists("ADMIN");
		ensureRoleExists("AGENT");
		ensureRoleExists("USER");

		ensureUserExists("admin", "123");
		ensureUserExists("nadine", "123");
		ensureUserExists("user1", "123");

		userService.addRoleToUser("admin", "ADMIN");
		userService.addRoleToUser("nadine", "AGENT");
		userService.addRoleToUser("user1", "USER");
	}

	private void ensureRoleExists(String roleName) {
		if (userService.findRolesByName(roleName).isEmpty()) {
			userService.addRole(new Role(null, roleName));
		}
	}

	private void ensureUserExists(String username, String password) {
		if (userService.findUserByUsername(username) == null) {
			userService.saveUser(new User(null, username, password, true, null));
		}
	}

	@Override
	public void run(String... args) throws Exception {
	}
}

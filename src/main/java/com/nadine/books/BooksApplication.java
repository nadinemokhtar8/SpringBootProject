package com.nadine.books;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nadine.books.genres.Book;
import com.nadine.books.genres.Role;
import com.nadine.books.genres.User;
import com.nadine.books.service.BookService;
import com.nadine.books.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class BooksApplication implements CommandLineRunner {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}
	@PostConstruct
	void init_users() {
	    // add roles
	    userService.addRole(new Role(null, "ADMIN"));
	    userService.addRole(new Role(null, "AGENT"));
	    userService.addRole(new Role(null, "USER"));

	    // save users WITH encoded passwords ✅
	    userService.saveUser(new User(null, "admin", "123", true, null));
	    userService.saveUser(new User(null, "nadine", "123", true, null));
	    userService.saveUser(new User(null, "user1",  "123", true, null));

	    // assign roles
	    userService.addRoleToUser("admin",  "ADMIN");
	    userService.addRoleToUser("nadine", "AGENT");
	    userService.addRoleToUser("user1",  "USER");
	}
	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Password Encoded BCRYPT :******************** ");
		// System.out.println(passwordEncoder.encode("123"));
		/*bookService.saveProduit(new Book("Insatiable",10.10,new Date()));
		bookService.saveProduit(new Book("Voracious",15.10,new Date()));
		bookService.saveProduit(new Book("Grovel",12.10,new Date()));*/


		
	}

}

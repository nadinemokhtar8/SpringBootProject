package com.nadine.books;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nadine.books.genres.Book;
import com.nadine.books.service.BookService;

@SpringBootApplication
public class BooksApplication implements CommandLineRunner {
	@Autowired
	BookService bookService;
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*bookService.saveProduit(new Book("Insatiable",10.10,new Date()));
		bookService.saveProduit(new Book("Voracious",15.10,new Date()));
		bookService.saveProduit(new Book("Grovel",12.10,new Date()));*/


		
	}

}

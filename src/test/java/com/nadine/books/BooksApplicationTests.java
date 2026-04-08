package com.nadine.books;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.nadine.books.genres.Book;
import com.nadine.books.repos.BookRepository;
import com.nadine.books.service.BookService;

@SpringBootTest
@Transactional
class BooksApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;

	@Test
	void contextLoads() {
		assertThat(bookRepository).isNotNull();
		assertThat(bookService).isNotNull();
	}

	@Test
	void shouldSaveAndFindBookByName() {
		Book book = new Book("Restitution", 11.5, new Date());
		bookRepository.save(book);

		List<Book> books = bookRepository.findByBookName("Restitution");

		assertThat(books).isNotEmpty();
		assertThat(books.get(0).getBookName()).isEqualTo("Restitution");
	}

	@Test
	void shouldReturnPagedBooksFromService() {
		bookRepository.save(new Book("Atlas", 20.0, new Date()));
		bookRepository.save(new Book("Babel", 25.0, new Date()));

		assertThat(bookService.getAllProduitsParPage(0, 2).getContent()).hasSizeGreaterThanOrEqualTo(2);
	}
}

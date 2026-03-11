package com.nadine.books;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.nadine.books.genres.Book;
import com.nadine.books.genres.Genre;
import com.nadine.books.repos.BookRepository;
import com.nadine.books.service.BookService;

@SpringBootTest
class BooksApplicationTests {

	@Autowired
	private BookRepository bookRepository;
	private BookService bookService;

	@Test
	public void testCreateProduit() {
		Book book = new Book("Restitution", 11.500, new Date());
		bookRepository.save(book);
	}
	@Test
	public void testFindProduit()
	{
		Book p = bookRepository.findById(2L).get();
		System.out.println(p);
	}
	@Test
	public void testUpdateProduit()
	{
		Book p = bookRepository.findById(1L).get();
		p.setBookPrice(1000.0);
		bookRepository.save(p);
	}
	@Test
	public void testDeleteProduit()
	{
		bookRepository.deleteById(1L);
	}

	@Test
	public void testListerTousProduits()
	{
		List<Book> prods = bookRepository.findAll();
		for (Book p : prods)
		{
			System.out.println(p); }
	}
	@Test
	public void testFindByNomProduitContains()
	{
		Page<Book> books = bookService.getAllProduitsParPage(0,4);
		System.out.println(books.getSize());
		System.out.println(books.getTotalElements());
		System.out.println(books.getTotalPages());
		books.getContent().forEach(p -> {System.out.println(p.toString());
		});

	}
	@Test
	public void testFindProduitByName()
	{
		List<Book> books = bookRepository.findByBookName("Restitution");
		for (Book p : books)
		{
			System.out.println(p); }
	}
	@Test
	public void testFindProduitByNameContains()
	{
		List<Book> books = bookRepository.findByBookNameContains("R");
		for (Book p : books)
		{
			System.out.println(p); }
	}
	@Test
	public void testFindProduitByNamePrice()
	{
		List<Book> books = bookRepository.findByBookNameAndPrice("Voracious", 15.1);
		for (Book p : books)
		{
			System.out.println(p); }
	}
	@Test
	public void testfindByCategorie()
	{
		Genre genre = new Genre();
		genre.setIdG(1L);
		List<Book> books = bookRepository.findByGenre(genre);
		for (Book p : books)
		{
			System.out.println(p);
		}
	}
	@Test
	public void findByCategorieIdCat()
	{
		List<Book> books = bookRepository.findByGenreIdG(1L);
		for (Book p : books)
		{
			System.out.println(p);
		}
	}
	@Test
	public void testfindByOrderByNomProduitAsc()
	{
		List<Book> books = 
				bookRepository.findByOrderByBookNameAsc();
		for (Book p : books)
		{
			System.out.println(p);
		}
	}
	@Test
	public void testTrierProduitsNomsPrix()
	{
		List<Book> books = bookRepository.trierBookNameBookPrice();
		for (Book p : books)
		{
			System.out.println(p);
		}
	}

}
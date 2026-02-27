package com.nadine.books;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.nadine.books.genres.Book;
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
    Book p = bookRepository.findById(1L).get();
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
}
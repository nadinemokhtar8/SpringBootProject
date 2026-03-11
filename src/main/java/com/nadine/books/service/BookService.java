package com.nadine.books.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nadine.books.genres.Book;
import com.nadine.books.genres.Genre;

public interface BookService {

    Book saveProduit(Book p);
    Book updateProduit(Book p);
    void deleteProduit(Book p);
    void deleteProduitById(Long id);
    Book getProduit(Long id);
    List<Book> getAllProduits();
    Page<Book> getAllProduitsParPage(int page, int size);
    List<Book> findByBookName(String bookName);
    List<Book> findByBookNameContains(String name);
    List<Book> findByBookNameAndPrice(String name, Double price);
    List<Book> findByGenre(Genre genre);
    List<Book> findByGenreIdG(Long id);
    List<Book> findByOrderByBookNameAsc();
    List<Book> trierBookNameBookPrice();
    List<Genre> getAllGenres();
}
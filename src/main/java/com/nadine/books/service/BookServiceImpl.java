package com.nadine.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nadine.books.genres.Book;
import com.nadine.books.genres.Genre;
import com.nadine.books.repos.BookRepository;
import com.nadine.books.repos.GenreRepository;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	GenreRepository genreRepository;

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {  
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Book saveProduit(Book p) {
        return bookRepository.save(p);
    }

    @Override
    public List<Book> findByBookName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    @Override
    public List<Book> findByBookNameContains(String name) {
        return bookRepository.findByBookNameContains(name);
    }

    @Override
    public List<Book> findByBookNameAndPrice(String name, Double price) {
        return bookRepository.findByBookNameAndPrice(name, price);
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> findByGenreIdG(Long id) {
        return bookRepository.findByGenreIdG(id);
    }

    @Override
    public List<Book> findByOrderByBookNameAsc() {
        return bookRepository.findByOrderByBookNameAsc();
    }


    @Override
    public List<Book> trierBookNameBookPrice() {
        return bookRepository.trierBookNameBookPrice();
    }

    @Override
    public Book updateProduit(Book p) {
        return bookRepository.save(p);
    }

    @Override
    public void deleteProduit(Book p) {
        bookRepository.delete(p);
    }

    @Override
    public void deleteProduitById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getProduit(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> getAllProduits() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> getAllProduitsParPage(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size));
    }

	@Override
	public List<Genre> getAllGenres() {
		return genreRepository.findAll();
	}
}
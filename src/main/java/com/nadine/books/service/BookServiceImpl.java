package com.nadine.books.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nadine.books.genres.Book;
import com.nadine.books.repos.BookRepository;

import jakarta.transaction.Transactional;
@Service

public class BookServiceImpl implements BookService{
	BookRepository bookRepository;
	 public BookServiceImpl(BookRepository bookRepository) {  // Spring injects automatically
	        this.bookRepository = bookRepository;
	    }
	@Override
	@Transactional
	public Book saveProduit(Book p) {
		return bookRepository.save(p);}
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
	return bookRepository.findById(id).get();
	}
	@Override
	public List<Book> getAllProduits() {
	return bookRepository.findAll();
	}
	@Override
	public Page<Book> getAllProduitsParPage(int page, int size) {
		return bookRepository.findAll(PageRequest.of(page, size));
	}


}

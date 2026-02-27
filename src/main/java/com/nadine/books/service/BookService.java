package com.nadine.books.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nadine.books.genres.Book;

public interface BookService {
	Book saveProduit(Book p);
	Book updateProduit(Book p);
	void deleteProduit(Book p);
	 void deleteProduitById(Long id);
	Book getProduit(Long id);
	List<Book> getAllProduits();
	 Page<Book> getAllProduitsParPage(int page, int size);
	}



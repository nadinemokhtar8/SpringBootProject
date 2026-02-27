package com.nadine.books.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadine.books.genres.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}

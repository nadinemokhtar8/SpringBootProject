package com.nadine.books.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nadine.books.genres.Book;
import com.nadine.books.genres.Genre;

public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("SELECT b FROM Book b WHERE b.BookName = :bookName")
    List<Book> findByBookName(@Param("bookName") String bookName);
	@Query("SELECT b FROM Book b WHERE b.BookName LIKE %:name%")
    List<Book> findByBookNameContains(@Param("name") String name);
	/*@Query("SELECT b FROM Book b WHERE b.BookName LIKE %?1% AND b.BookPrice > ?2")
    List<Book> findByBookNameBookPrice(String name, Double price);*/
	 @Query("SELECT b FROM Book b WHERE b.BookName LIKE %:name% AND b.BookPrice = :price")
	    List<Book> findByBookNameAndPrice(
	        @Param("name") String name, 
	        @Param("price") Double price
	    );
	 @Query("select p from Book p where p.genre = ?1")
	 List<Book> findByGenre (Genre genre);
	 List<Book> findByGenreIdG(Long id);
	 List<Book> findByOrderByBookNameAsc();
	 @Query("select p from Book p order by p.BookName ASC, p.BookPrice DESC")
	 List<Book> trierBookNameBookPrice ();
}

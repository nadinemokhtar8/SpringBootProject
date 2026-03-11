package com.nadine.books.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadine.books.genres.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}

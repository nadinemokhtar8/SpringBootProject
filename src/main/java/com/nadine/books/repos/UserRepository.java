package com.nadine.books.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadine.books.genres.User;

public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername (String username);
}

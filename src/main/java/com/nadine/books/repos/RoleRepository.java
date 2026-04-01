package com.nadine.books.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadine.books.genres.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
Role findByRole(String role);
}
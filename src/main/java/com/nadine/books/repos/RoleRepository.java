package com.nadine.books.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadine.books.genres.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
List<Role> findByRole(String role);
}

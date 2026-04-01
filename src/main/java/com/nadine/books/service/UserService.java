package com.nadine.books.service;

import com.nadine.books.genres.Role;
import com.nadine.books.genres.User;

public interface UserService {
void deleteAllusers();
void deleteAllRoles();
User saveUser(User user);
User findUserByUsername (String username);
Role addRole(Role role);
void addRoleToUser(String username, String rolename);
}

package com.nadine.books.service;

import java.util.List;

import com.nadine.books.genres.Role;
import com.nadine.books.genres.User;

public interface UserService {
void deleteAllusers();
void deleteAllRoles();
User saveUser(User user);
User findUserByUsername (String username);
Role addRole(Role role);
List<Role> findRolesByName(String roleName);
void addRoleToUser(String username, String rolename);
}

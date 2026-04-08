package com.nadine.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nadine.books.genres.Role;
import com.nadine.books.genres.User;
import com.nadine.books.repos.RoleRepository;
import com.nadine.books.repos.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRep;
	@Autowired
	RoleRepository roleRep;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRep.save(user);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
	    User usr = userRep.findByUsername(username);
	    if (usr == null) {
	        throw new RuntimeException("User not found: " + username);
	    }
	    List<Role> roles = roleRep.findByRole(roleName);
	    if (roles.isEmpty()) {
	        throw new RuntimeException("Role not found: " + roleName);
	    }
	    Role role = roles.get(0);
	    if (usr.getRoles().stream().anyMatch(existingRole -> existingRole.getRole().equals(roleName))) {
	    	return;
	    }
	    usr.getRoles().add(role);
	    userRep.save(usr);
	}

	@Override
	public void deleteAllusers() {
		userRep.deleteAll();
	}

	@Override
	public void deleteAllRoles() {
		roleRep.deleteAll();
	}

	@Override
	public Role addRole(Role role) {
		return roleRep.save(role);
	}

	@Override
	public List<Role> findRolesByName(String roleName) {
		return roleRep.findByRole(roleName);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRep.findByUsername(username);
	}
}

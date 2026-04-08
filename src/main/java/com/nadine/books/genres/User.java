package com.nadine.books.genres;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true)
    private String username;
    private String password;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    // Constructors
    public User() {}

    public User(Long user_id, String username, String password, Boolean enabled, List<Role> roles) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles != null ? roles : new ArrayList<>();
    }

    // Getters
    public Long getUser_id() { return user_id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Boolean getEnabled() { return enabled; }
    public List<Role> getRoles() { return roles; }

    // Setters
    public void setUser_id(Long user_id) { this.user_id = user_id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    public void setRoles(List<Role> roles) { this.roles = roles; }

    @Override
    public String toString() {
        return "User{user_id=" + user_id +
               ", username='" + username + "'" +
               ", enabled=" + enabled +
               ", roles=" + roles + "}";
    }
}

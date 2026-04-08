package com.nadine.books.genres;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdG;
	private String NameG;
	private String descriptionG;
	@OneToMany (mappedBy="genre")
	@JsonIgnore
	private List<Book>books;
	
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public Genre() {
		super();
	}
	public Long getIdG() {
		return IdG;
	}
	public void setIdG(Long idG) {
		IdG = idG;
	}
	public String getNameG() {
		return NameG;
	}
	public void setNameG(String nameG) {
		NameG = nameG;
	}
	public String getDescriptionG() {
		return descriptionG;
	}
	public void setDescriptionG(String descriptionG) {
		this.descriptionG = descriptionG;
	}
	

}

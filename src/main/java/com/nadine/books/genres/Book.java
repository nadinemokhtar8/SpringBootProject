package com.nadine.books.genres;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBook;
	public Long getIdBook() {
		return idBook;
	}
	public void setIdBook(Long idBook) {
		this.idBook = idBook;
	}
	private String BookName;
	private Double BookPrice;
	private Date dateCreation;
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public Double getBookPrice() {
		return BookPrice;
	}
	public void setBookPrice(Double bookPrice) {
		BookPrice = bookPrice;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Book() {
	}
	public Book( String bookName, Double bookPrice, Date dateCreation) {
		super();
		BookName = bookName;
		BookPrice = bookPrice;
		this.dateCreation = dateCreation;
	}
	@Override
	public String toString() {
		return "Book [idBook=" + idBook + ", BookName=" + BookName + ", BookPrice=" + BookPrice + ", dateCreation="
				+ dateCreation + "]";
	}




}

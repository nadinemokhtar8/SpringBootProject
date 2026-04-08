package com.nadine.books.genres;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBook;
	@ManyToOne
	private Genre genre;
	@NotNull
	@Size(min = 4, max = 15)
	private String BookName;
	@Min(value = 10)
	@Max(value = 10000)
	private Double BookPrice;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private Date dateCreation;

	public Long getIdBook() {
		return idBook;
	}

	public void setIdBook(Long idBook) {
		this.idBook = idBook;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String BookName) {
		this.BookName = BookName;
	}

	public Double getBookPrice() {
		return BookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.BookPrice = bookPrice;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Book() {
	}

	public Book(String bookName, Double bookPrice, Date dateCreation) {
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}

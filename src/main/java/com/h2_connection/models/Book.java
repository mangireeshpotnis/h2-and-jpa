package com.h2_connection.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
@Entity
public class Book {
	@Id //for making it primary key
	@GeneratedValue(strategy = GenerationType.AUTO) //for making it autoincrement
	int id;
	@Size(min = 1, max=10, message="Book name has to be between 1 to 10 characters long!")
	@NotNull(message = "Book name cannot be null!")
	@Column(unique=true, nullable = false)
	String bookName;
	@NotNull(message = "Author name cannot be null!")
	@Column(nullable = false)
	String authorName;
	@Min(value=200,message ="price has to be at least 200")
	@Column(nullable = false)
	int price;
	
	public Book() {
		
	}
	
	
	public Book(int id, String bookName, String authorName, int price) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.authorName = authorName;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}

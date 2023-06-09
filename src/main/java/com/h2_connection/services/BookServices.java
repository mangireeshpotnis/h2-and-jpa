package com.h2_connection.services;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.h2_connection.models.Book;
import com.h2_connection.repositories.BookRepository;

@Service
public class BookServices {

	@Autowired
	BookRepository bookRepository;


	/*get	
	public Collection<Book> getBook() {
		return bookMap.values();
	}
	 */	
	//get all books  using CRUD methods
	public Iterable<Book> getAllBooks() {
		Iterable<Book> allBooks = bookRepository.findAll();
		return allBooks;
	}

	/*getById
	public Book getBookById(int id) {
		Book book = bookMap.get(id);

		return book;
	}
	 */
	//get books by id using CRUD methods
	public Book getBookById(int id) {
		return bookRepository.findById(id).orElseThrow(
				()-> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist!");
					});		
	}

	/*writebook
	public String addBook(Book book) {
		int id= atomicInt.incrementAndGet();
		book.setId(id);
		bookMap.put(id, book);
		return "book added successfully";
	}
	 */	
	//add new book
	public void addBook(Book book) {
		bookRepository.save(book);
	}

	/*updateBookBy id using CRUD methods
	public String updateBookById(int id, Book book) {
		book.setId(id);
		bookMap.put(id, book);
		return "book updated successfully";
	}
	 */
	//update book by id using CRUD methods
	public void updateBookById(int id, Book book) {
		this.getBookById(id);
		book.setId(id);
		bookRepository.save(book);
	}

	/*deleteBook by id
	public String deleteBookbyid(int id) {
		bookMap.remove(id);
		return "book deleted successfully";
	}
	 */
	//delete book by id using CRUD methods
	 public void delete(int id) 
	   {
	    getBookById(id);
	    bookRepository.deleteById(id);
	   }


}

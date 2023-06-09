package com.h2_connection.controllers;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.h2_connection.helpers.BookResponseWrapper;
import com.h2_connection.models.Book;
import com.h2_connection.services.BookServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookServices bookServices;

	@GetMapping("")
	public ResponseEntity<?> getBook() {
		Iterable<Book> booksData =  bookServices.getAllBooks();
		Iterator<Book> allBooks = booksData.iterator();

		if(!(allBooks.hasNext())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"there are no records available at this point");			
		}
		else {
			BookResponseWrapper bw = new BookResponseWrapper();
			bw.setMessage("Currently available books");
			bw.setData(allBooks);
			return new ResponseEntity<>(bw,HttpStatus.FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable int id) {
		Book booksData = bookServices.getBookById(id);

		BookResponseWrapper bw = new BookResponseWrapper();
		bw.setMessage("Book with the ID you requested");
		bw.setData(booksData);
		return new ResponseEntity<>(bw,HttpStatus.FOUND);
	}



	@PostMapping("")
	public ResponseEntity<?> addBook(@RequestBody @Valid Book book) {
		bookServices.addBook(book);		
		return new ResponseEntity<>("Book added",HttpStatus.CREATED);
	}


	@PutMapping("")
	public ResponseEntity<?> updateBookById(@RequestParam int id,@Valid @RequestBody Book book) {
		 Book bookAtId = bookServices.getBookById(id);
		
		bookServices.updateBookById(id, book);
		return new ResponseEntity<>("Book updated sussessfully",HttpStatus.OK);		

	}



	
	  @DeleteMapping("/{id}")
	   public ResponseEntity<?> deleteUser(@PathVariable int id)
	   {
	    Book book_data=bookServices.getBookById(id);
	  
	     bookServices.delete(id);
	     return new ResponseEntity<>("Book deleted successfully",HttpStatus.OK);
	     
	   }


}

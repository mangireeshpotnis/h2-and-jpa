package com.h2_connection.repositories;

import org.springframework.data.repository.CrudRepository;

import com.h2_connection.models.Book;

public interface BookRepository extends CrudRepository<Book,Integer>{
	// <Model, Datatype of primary key of the model>
	

}

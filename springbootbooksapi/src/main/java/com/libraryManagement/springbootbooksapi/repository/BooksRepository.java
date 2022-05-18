package com.libraryManagement.springbootbooksapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.libraryManagement.springbootbooksapi.model.Books;

@Repository
public interface BooksRepository extends CrudRepository<Books, String>{

	
}

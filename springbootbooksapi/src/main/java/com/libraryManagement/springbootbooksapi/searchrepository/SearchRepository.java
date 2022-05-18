package com.libraryManagement.springbootbooksapi.searchrepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.libraryManagement.springbootbooksapi.model.Books;

@Repository
public interface SearchRepository extends JpaRepository<Books, Integer>{
	 List<Books> findByTitle(String title);
	 List<Books> findByAuthor(String author);
	 List<Books> findByPrice(int price);
	 List<Books> findByGenre(String genre);
	
	
}

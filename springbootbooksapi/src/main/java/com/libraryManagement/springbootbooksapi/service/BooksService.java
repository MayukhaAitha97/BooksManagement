package com.libraryManagement.springbootbooksapi.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libraryManagement.springbootbooksapi.model.Books;
import com.libraryManagement.springbootbooksapi.repository.BooksRepository;
import com.libraryManagement.springbootbooksapi.searchrepository.SearchRepository;

@Service
public class BooksService {

	@Autowired
	BooksRepository booksRepository;
	@Autowired
	SearchRepository searchRepository;
	
// upload all books from csv file
	public String uploadBooks(List<Books> books) {
		booksRepository.saveAll(books);
		return "Succesfully uploaded the books into the library";
	}
// adding books into library
	public String addBooks(List<Books> books) {
		booksRepository.saveAll(books);
		return "Succesfully added the books into the library";
	}
// getting all books from library
	public List<Books> getAllBooks(){
		List<Books> books = new ArrayList<>();
		booksRepository.findAll().forEach(book1->books.add(book1));
		return books;
	}
// getting books from library by isbn
	public Books getbyISBN(int isbn) {
		return booksRepository.findById(isbn).get();
	}
// getting books from library by title
	public List<Books> getbyTitle(String title){
		List<Books> books = new ArrayList<>();
		searchRepository.findByTitle(title).forEach(book1->books.add(book1));
		return books;
	}
// getting books from library by author
	public List<Books> getbyAuthor(String author){
		List<Books> books = new ArrayList<>();
		searchRepository.findByAuthor(author).forEach(book1->books.add(book1));
		return books;
	}
// getting books from library by price
	public List<Books> getbyPrice(int price){
		List<Books> books = new ArrayList<>();
		searchRepository.findByPrice(price).forEach(book1->books.add(book1));
		return books;
	}
// getting books from library by genre
	public List<Books> getbyGenre(String genre){
		List<Books> books = new ArrayList<>();
		searchRepository.findByGenre(genre).forEach(book1->books.add(book1));
		return books;
	}
// updating books in the library
	public List<Books> updateBooks(List<Books> books) {
		booksRepository.saveAll(books);
		return books;	
	}
// deleting books from library
	public String deleteBooks(List<Integer> isbns) {
		booksRepository.deleteAllById(isbns);
		return "Succesfully deleted the books from the library";
	}

}

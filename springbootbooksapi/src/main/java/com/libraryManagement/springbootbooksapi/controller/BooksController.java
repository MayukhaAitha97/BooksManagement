package com.libraryManagement.springbootbooksapi.controller;

import java.io.InputStream;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.libraryManagement.springbootbooksapi.model.Books;
import com.libraryManagement.springbootbooksapi.service.BooksService;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@RestController
@RequestMapping("/api")
public class BooksController {
	@Autowired
	BooksService booksService;
	
	
	//uploading books from inputed csv file 
	@PostMapping("/upload")
	public String uploadData(@RequestParam("file") MultipartFile file)throws Exception{
		List<Books> books = new ArrayList<>();
		InputStream inputstream = file.getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		List<com.univocity.parsers.common.record.Record> parseAllRecords = parser.parseAllRecords(inputstream);
		parseAllRecords.forEach(record->{
			Books book = new Books();
			book.setIsbn(Integer.parseInt(record.getString("isbn")));
			book.setAuthor(record.getString("author"));
			book.setGenre(record.getString("genre"));
			book.setPrice(Integer.parseInt(record.getString("price")));
			book.setTitle(record.getString("title"));
			books.add(book);
		});
		booksService.uploadBooks(books);
		return "Upload Successful";
	}
	
	//adding books by mapping books from request
	@PostMapping("/books")
	@ResponseStatus(HttpStatus.CREATED)
	
	public String  addingBooks(@RequestBody List<Books> books) {
		return booksService.addBooks(books);
	}
	
	//getting books
	@GetMapping("/books")
	public List<Books> gettingAllBooks() {
		return booksService.getAllBooks();
	}
	
	// getting book by mapping isbn from request
	@GetMapping("/book/isbn/{isbn}")
	public Books gettingByISBN(@PathVariable int isbn) {
		return booksService.getbyISBN(isbn);
	}
	
	// getting book by mapping title from request
	@GetMapping("/book/title/{title}")
	public List<Books> gettingbyTitle(@PathVariable String title){
		return booksService.getbyTitle(title);
	}
	
	// getting book by mapping author from request
	@GetMapping("/book/author/{author}")
	public List<Books> gettingbyAuthor(@PathVariable String author){
		return booksService.getbyAuthor(author);
	}
	
	// getting book by mapping price from request
	@GetMapping("/book/price/{price}")
	public List<Books> gettingbyPrice(@PathVariable int price){
		return booksService.getbyPrice(price);
	}
	
	// getting book by mapping genre from request
	@GetMapping("/book/genre/{genre}")
	public List<Books> gettingbyGenre(@PathVariable String genre){
		return booksService.getbyGenre(genre);
	}

	//updating books using isbn from request
	@PutMapping("/books")
	public List<Books> updatingBooks(@RequestBody List<Books> books){
		return booksService.updateBooks(books);
	}
	
	//deleting books using isbn from request
	@DeleteMapping("/books")
	public String  deletingBooks(@RequestBody List<Integer> isbns){
		return booksService.deleteBooks(isbns);
	}
	
}

package com.libraryManagement.springbootbooksapi;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import java.util.ArrayList;
import java.util.List; 
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryManagement.springbootbooksapi.model.Books;
import com.libraryManagement.springbootbooksapi.service.BooksService;
 
@WebMvcTest
public class BooksControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private BooksService booksService;
 
    private static ObjectMapper mapper = new ObjectMapper();
 
    
    //test for getting books
    @Test
    public void testGetBooks() throws Exception {
        List<Books> books = new ArrayList<>();
        Books book = new Books();
        book.setIsbn(1);
        book.setAuthor("Jonathan Swift");
        book.setGenre("Adventure fiction");
        book.setTitle("Gulliver's Travels");
        book.setPrice(100);
        books.add(book);
        Mockito.when(booksService.getAllBooks()).thenReturn(books);
        mockMvc.perform(get("/api/books")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].author", Matchers.equalTo("Jonathan Swift")));
    }
    
    //test for adding books
    @Test
    public void testAddingBooks() throws Exception {
    	List<Books> books = new ArrayList<>();
        Books book = new Books();
        book.setIsbn(1);
        book.setAuthor("Jonathan Swift");
        book.setGenre("Adventure fiction");
        book.setTitle("Gulliver's Travels");
        book.setPrice(100);
        books.add(book);
        Mockito.when(booksService.addBooks(ArgumentMatchers.any())).thenReturn("Succesfully added the books into the library");
        String json = mapper.writeValueAsString(books);
        MvcResult requestResult= mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        
      
      String result = requestResult.getResponse().getContentAsString();
      assertEquals(result, "Succesfully added the books into the library");
        
    }
 
    //test for updating books
    @Test
    public void testUpdatingBooks() throws Exception {
    	List<Books> books = new ArrayList<>();
        Books book = new Books();
        book.setIsbn(1);
        book.setAuthor("Jonathan Swift");
        book.setGenre("Adventure fiction");
        book.setTitle("Gulliver's Travels");
        book.setPrice(100);
        books.add(book);
        Mockito.when(booksService.updateBooks(ArgumentMatchers.any())).thenReturn(books);
        String json = mapper.writeValueAsString(books);
        mockMvc.perform(put("/api/books").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(1));
    }
 
    //test for deleting books
    @Test
    public void testDeleteExample() throws Exception {
    	List<Integer> isbns = new ArrayList<>();
    	isbns.add(1);
        Mockito.when(booksService.deleteBooks(ArgumentMatchers.any())).thenReturn("Succesfully deleted the books from the library");
        
        String json = mapper.writeValueAsString(isbns);
        MvcResult requestResult= mockMvc.perform(delete("/api/books").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
      String result = requestResult.getResponse().getContentAsString();
      assertEquals(result, "Succesfully deleted the books from the library");
    }
    
    //test for getting books by author
    @Test
    public void testGetBooksByAuthor() throws Exception {
        List<Books> books = new ArrayList<>();
        Books book = new Books();
        book.setIsbn(1);
        book.setAuthor("Jonathan Swift");
        book.setGenre("Adventure fiction");
        book.setTitle("Gulliver's Travels");
        book.setPrice(100);
        books.add(book);
        String author = "Jonathan Swift";
        
        Mockito.when(booksService.getbyAuthor(ArgumentMatchers.anyString())).thenReturn(books);
        String json = mapper.writeValueAsString(author);
        mockMvc.perform(get("/api/book/author/{author}","Jonathan Swift").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].author", Matchers.equalTo("Jonathan Swift"))); 
    }
    
    //test for getting books by title
    @Test
    public void testGetBooksByTitle() throws Exception {
        List<Books> books = new ArrayList<>();
        Books book = new Books();
        book.setIsbn(1);
        book.setAuthor("Jonathan Swift");
        book.setGenre("Adventure fiction");
        book.setTitle("Gulliver's Travels");
        book.setPrice(100);
        books.add(book);
        String title = "Gulliver's Travels";
        
        Mockito.when(booksService.getbyTitle(ArgumentMatchers.anyString())).thenReturn(books);
        String json = mapper.writeValueAsString(title);
        mockMvc.perform(get("/api/book/title/{title}","Gulliver's Travels").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].title", Matchers.equalTo("Gulliver's Travels")));
  
    }
    
  //test for getting books by genre
    @Test
    public void testGetBooksByGenre() throws Exception {
        List<Books> books = new ArrayList<>();
        Books book = new Books();
        book.setIsbn(1);
        book.setAuthor("Jonathan Swift");
        book.setGenre("Adventure fiction");
        book.setTitle("Gulliver's Travels");
        book.setPrice(100);
        books.add(book);
        String genre = "Adventure fiction";
        
        Mockito.when(booksService.getbyGenre(ArgumentMatchers.anyString())).thenReturn(books);
        String json = mapper.writeValueAsString(genre);
        mockMvc.perform(get("/api/book/genre/{genre}","Adventure fiction").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].genre", Matchers.equalTo("Adventure fiction")));
  
    }
    
  //test for getting books by price
    @Test
    public void testGetBooksByPrice() throws Exception {
        List<Books> books = new ArrayList<>();
        Books book = new Books();
        book.setIsbn(1);
        book.setAuthor("Jonathan Swift");
        book.setGenre("Adventure fiction");
        book.setTitle("Gulliver's Travels");
        book.setPrice(100);
        books.add(book);
        int price = 100;
        
        Mockito.when(booksService.getbyPrice(ArgumentMatchers.anyInt())).thenReturn(books);
        String json = mapper.writeValueAsString(price);
        mockMvc.perform(get("/api/book/price/{price}",100).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].price", Matchers.equalTo(100)));
  
    }
  
  //test for getting books by isbn
    @Test
    public void testGetBooksByIsbn() throws Exception {
        
        Books book = new Books();
        book.setIsbn(1);
        book.setAuthor("Jonathan Swift");
        book.setGenre("Adventure fiction");
        book.setTitle("Gulliver's Travels");
        book.setPrice(100);
        
        int isbn = 1;
        
        Mockito.when(booksService.getbyISBN(ArgumentMatchers.anyInt())).thenReturn(book);
        String json = mapper.writeValueAsString(isbn);
        mockMvc.perform(get("/api/book/isbn/{isbn}",1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(1));
    }
  
  
    
}
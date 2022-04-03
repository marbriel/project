package com.library.resources;


import com.library.models.Book;
import com.library.services.implementation.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping("/api/book")
public class BookResource {

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/list")
    public ResponseEntity<Optional<List<Book>>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllAvailableBooks(), OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Book>> getABookById(@PathVariable Long id){
        return new ResponseEntity<>(bookService.getBookById(id), OK);
    }

    @GetMapping("/author")
    public ResponseEntity<Optional<List<Book>>> getBooksByAuthor(@RequestParam("author") String author){
        return new ResponseEntity<>(bookService.getBookByAuthor(author), OK);
    }

    @GetMapping("/title")
    public ResponseEntity<Optional<Book>> getBookByTitle(@RequestParam("title") String title){
        return new ResponseEntity<>(bookService.getBookByTitle(title), OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Optional<Book>> saveABook(@RequestBody Book book){
        ResponseEntity<Optional<Book>> response;
        if(book.getId() == null){
            response = new ResponseEntity<>(bookService.saveABook(book), CREATED);
        }else{
            response= new ResponseEntity<>(bookService.updateBookInfo(book), OK);
        }

        return response;
    }

    @PutMapping("/restock/{id}")
    public ResponseEntity<Optional<Book>> reStockBook(@PathVariable Long id){
        return new ResponseEntity<>(bookService.reStockBook(id), OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Book>> deleteBook(@PathVariable Long id){
        return  new ResponseEntity<>(bookService.deleteBook(id), OK);
    }


}

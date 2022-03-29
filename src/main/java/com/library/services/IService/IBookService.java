package com.library.services.IService;

import com.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    Optional<List<Book>> getAllBooks();
    Optional<Book> getBookById(Long id);
    Optional<List<Book>> getBookByAuthor(String author);
    Optional<Book> getBookByTitle(String bookTitle);
    void saveABook(Book book);
    void deleteBook(Long bookId);
}

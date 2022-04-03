package com.library.services.IService;

import com.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    Optional<List<Book>> getAllBooks();
    Optional<List<Book>> listOfOutStockBooks();
    Optional<List<Book>> getAllAvailableBooks();
    Optional<Book> getBookById(Long id);
    Optional<List<Book>> getBookByAuthor(String author);
    Optional<Book> getBookByTitle(String bookTitle);
    Optional<Book> updateBookInfo(Book book);
    Optional<Book> saveABook(Book book);
    Optional<Book> deleteBook(Long bookId);
    Optional<Book> reStockBook(Long id);
}

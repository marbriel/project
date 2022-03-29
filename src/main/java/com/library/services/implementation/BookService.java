package com.library.services.implementation;

import com.library.models.Book;
import com.library.repositories.BookRepository;
import com.library.services.IService.IBookService;
import com.library.utils.StringManipulator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Repository
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    @Override
    public Optional<List<Book>> getAllBooks() {
        Optional<List<Book>> fetchAllBooks = Optional.of(bookRepository.findAll());
        fetchAllBooks.ifPresentOrElse(ArrayList::new, ()->{
            try{
                throw new NullPointerException("No Books are currently available");
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        });
        return fetchAllBooks;
    }

    @Override
    public Optional<Book> getBookById(Long id) {
       return Optional.of(bookRepository.findById(id).orElseThrow(null));
    }

    @Override
    public Optional<List<Book>> getBookByAuthor(String author) {
        return Optional.of(bookRepository.findByAuthor(author).orElseThrow(null));
    }

    @Override
    public Optional<Book> getBookByTitle(String bookTitle) {
        return Optional.of(bookRepository.findByTitle(bookTitle).orElseThrow(null));
    }

    @Override
    public void saveABook(Book book) {
        Optional<Book> bookOptional = Optional.of(book);
        bookOptional.ifPresent(bookToSave ->{
            bookToSave.setTitle(StringManipulator.upperEveryStarts(book.getTitle()));
            bookToSave.setAuthor(StringManipulator.upperEveryStarts(book.getAuthor()));
            bookToSave.setAvailableStocks(0);
            bookRepository.save(bookToSave);
        });
    }

    @Override
    public void deleteBook(Long bookId) {
        Optional<Book> bookToDelete = getBookById(bookId);
        bookToDelete.ifPresentOrElse(bookRepository::delete, ()->{
            throw new NullPointerException("No Book found");
        });
    }
}

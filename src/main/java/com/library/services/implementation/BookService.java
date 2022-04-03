package com.library.services.implementation;

import com.library.models.Book;
import com.library.models.SpecificBook;
import com.library.repositories.BookRepository;
import com.library.repositories.SpecificBookRepository;
import com.library.services.IService.IBookService;
import com.library.utils.StringManipulator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    @Lazy
    private final SpecificBookRepository specificBookRepository;

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
    public Optional<List<Book>> listOfOutStockBooks() {
        Optional<List<Book>> fetchAllBooks = (bookRepository.findByIsOnShelfFalse());
        fetchAllBooks.ifPresentOrElse(ArrayList::new, ()->{
            try{
                throw new NullPointerException("No Books To Show");
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        });
        return fetchAllBooks;
    }

    @Override
    public Optional<List<Book>> getAllAvailableBooks() {
        Optional<List<Book>> fetchAllBooks = (bookRepository.findByIsOnShelfTrue());
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
       return Optional.of(bookRepository.findById(id).orElseThrow(()->new RuntimeException("No book found")));
    }

    @Override
    public Optional<List<Book>> getBookByAuthor(String author) {
        return Optional.of(bookRepository.findByAuthorIgnoreCaseContaining(author).orElseThrow(()->new RuntimeException("No books found")));
    }

    @Override
    public Optional<Book> getBookByTitle(String bookTitle) {
        return Optional.of(bookRepository.findByTitleIgnoreCaseContaining(bookTitle).orElseThrow(()->new RuntimeException("No book found")));
    }

    @Override
    public Optional<Book> updateBookInfo(Book book) {
        Optional<Book> bookToUpdate = bookRepository.findById(book.getId());
        bookToUpdate.ifPresent(updateBook -> {
            updateBook.setTitle(StringManipulator.upperEveryStarts(book.getTitle()));
            updateBook.setAuthor(StringManipulator.upperEveryStarts(book.getAuthor()));
        });
        return bookToUpdate;
    }

    @Override
    public Optional<Book> saveABook(Book book) {
        Optional<Book> bookOptional = Optional.of(book);
        bookOptional.ifPresent(bookToSave ->{
            bookToSave.setTitle(StringManipulator.upperEveryStarts(book.getTitle()));
            bookToSave.setAuthor(StringManipulator.upperEveryStarts(book.getAuthor()));
            bookToSave.setAvailableStocks(0);
            bookToSave.setIsOnShelf(true);
            bookRepository.save(bookToSave);
        });

        return bookOptional;
    }

    @Override
    public Optional<Book> deleteBook(Long bookId) {
        Book bookToDelete = getBookById(bookId).orElseThrow(null);

        for (SpecificBook specificBook: bookToDelete.getSpecificBooks()){
            if(specificBook.getIsBorrowed()){
                throw new RuntimeException("Can't delete because some books are still on borrowers' hand");
            }
        }
        for(SpecificBook specificBook: bookToDelete.getSpecificBooks()){
            specificBook.setIsAvailable(false);
        }

        bookToDelete.setIsOnShelf(false);

        return Optional.of(bookToDelete);
    }

    @Override
    public Optional<Book> reStockBook(Long id) {
        Book bookToReStock = bookRepository.findById(id).orElseThrow(null);
        for(SpecificBook specificBook: bookToReStock.getSpecificBooks()){
            specificBook.setIsAvailable(true);
        }
        bookToReStock.setIsOnShelf(true);
        return Optional.of(bookToReStock);
    }
}

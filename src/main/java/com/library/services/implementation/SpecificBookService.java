package com.library.services.implementation;

import com.library.DAO.SpecificBookDAO;
import com.library.models.Book;
import com.library.models.SpecificBook;
import com.library.repositories.SpecificBookRepository;
import com.library.services.IService.ISpecificBook;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Transactional
public class SpecificBookService implements ISpecificBook {

    private final SpecificBookRepository repository;
    @Lazy
    private final BookService bookService;

    public SpecificBookService(SpecificBookRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }


    @Override
    public Optional<List<SpecificBook>> getAllSpecificBook(Long bookId) {
        Book book = bookService.getBookById(bookId).orElseThrow(null);
        Optional<List<SpecificBook>> allSpecificBooks = repository.findByBook(book);
        allSpecificBooks.ifPresentOrElse(ArrayList::new,
                () -> {
                    throw new NullPointerException("No books to shows");
                });
        return allSpecificBooks;
    }

    @Override
    public Optional<SpecificBook> getASpecificBook(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(null));
    }

    @Override
    public Optional<SpecificBookDAO> getSpecificBookByCode(String code) {
        SpecificBookDAO specificBookDAO = new SpecificBookDAO();
        return repository.findBySpecificBookCode(code)
                .map(specificBook -> {
                    specificBookDAO.setId(specificBook.getId());
                    specificBookDAO.setSpecificBookCode(specificBook.getSpecificBookCode());
                    specificBookDAO.setBookTitle(specificBook.getBook().getTitle());
                    specificBookDAO.setIsBorrowed(specificBook.getIsBorrowed());
                    specificBookDAO.setStatus(specificBook.getIsBorrowed());
                    return specificBookDAO;
                });
    }

    @Override
    public void saveASpecificBook(Long bookId) {
        SpecificBook specificBook = new SpecificBook();
        Book book = bookService.getBookById(bookId).orElseThrow(null);
        specificBook.setBook(book);
        specificBook.setSpecificBookCode();
        specificBook.setIsBorrowed(false);
        book.setAvailableStocks(book.getAvailableStocks() + 1);
        book.addSpecificBook(specificBook);
        log.warn("Saving a book with code of {}", specificBook.getSpecificBookCode());
        repository.save(specificBook);
    }

    @Override
    public void deleteSpecificBook(Long id) {
        SpecificBook specificBook = getASpecificBook(id).orElseThrow(null);
        Book book = specificBook.getBook();
        if(book.getSpecificBooks().contains(specificBook)){
            book.getSpecificBooks().remove(specificBook);
            book.setAvailableStocks(book.getAvailableStocks() - 1);
            log.warn("Removing specific book from the shelf of {} with code {}", book.getTitle(), specificBook.getSpecificBookCode());
            repository.delete(specificBook);
        }

    }
}

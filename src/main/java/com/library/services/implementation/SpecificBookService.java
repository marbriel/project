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
import java.util.stream.Collectors;

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
        return Optional.empty();
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
    public void saveABook(Long bookId, SpecificBook specificBook) {
        Book book = bookService.getBookById(bookId).orElseThrow(null);
        specificBook.setBook(book);
        book.setAvailableStocks(book.getAvailableStocks() + 1);
        book.addSpecificBook(specificBook);
        repository.save(specificBook);
    }

    @Override
    public void deleteBook(Long id) {

    }
}

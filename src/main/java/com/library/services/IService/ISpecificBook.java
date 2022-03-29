package com.library.services.IService;

import com.library.DAO.SpecificBookDAO;
import com.library.models.Book;
import com.library.models.SpecificBook;

import java.util.List;
import java.util.Optional;

public interface ISpecificBook {
    Optional<List<SpecificBook>> getAllSpecificBook(Long bookId);
    Optional<SpecificBook> getASpecificBook(Long id);
    Optional<SpecificBookDAO> getSpecificBookByCode(String code);
    void saveABook(Long bookId, SpecificBook specificBook);
    void deleteBook(Long id);
}

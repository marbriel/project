package com.library.repositories;

import com.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCaseContaining(@Param("title") String title);
    Optional<List<Book>> findByAuthorIgnoreCaseContaining(@Param("author") String author);
    Optional<List<Book>> findByIsOnShelfTrue();
    Optional<List<Book>> findByIsOnShelfFalse();
}

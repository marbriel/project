package com.library.repositories;

import com.library.models.SpecificBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecificBookRepository extends JpaRepository<SpecificBook, Long> {
    Optional<SpecificBook> findBySpecificBookCode(String specificBookCode);
    /*return all available specific book*/
    Optional<List<SpecificBook>> findByIsBorrowedFalse();
}

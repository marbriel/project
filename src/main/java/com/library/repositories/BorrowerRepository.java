package com.library.repositories;

import com.library.models.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Optional<Borrower> findByEmailAddressAndIsDeletedFalse(String emailAddress);
    Optional<Borrower> findByIdAndIsDeletedFalse(Long id);
    @Query("SELECT borrower FROM Borrower borrower WHERE borrower.isDeleted = false")
    Optional<List<Borrower>> getAllBorrowers();
    Optional<Borrower> findByEmailAddressAndIsDeletedTrue(String emailAddress);
}

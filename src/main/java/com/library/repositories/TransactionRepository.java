package com.library.repositories;

import com.library.models.Borrower;
import com.library.models.SpecificBook;
import com.library.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Optional<List<Transaction>> findByIsTransactionDoneFalse();
    Optional<Transaction> findByBorrower(Borrower borrower);
    Optional<Transaction> findBySpecificBook(SpecificBook specificBook);
    Optional<Transaction> findByDateExpectedReturn(Date expectedDateReturn);
}

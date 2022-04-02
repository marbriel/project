package com.library.services.IService;

import com.library.models.Borrower;
import com.library.utils.BorrowerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IBorrower {

    Optional<List<Borrower>> retrieveAllBorrower() throws BorrowerNotFoundException;
    Optional<Borrower> saveBorrower(Borrower borrower);
    Optional<Borrower> getBorrowerById(Long id);
    Optional<Borrower> getBorrowerByEmail(String email);
    Optional<Borrower> updateBorrower(Borrower borrower);
    Optional<Borrower> deleteBorrowerById(Long borrowerId);
    Optional<Borrower> retrieveAccount(String email);
}

package com.library.services.IService;

import com.library.models.Borrower;
import com.library.utils.BorrowerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IBorrower {

    Optional<List<Borrower>> retrieveAllBorrower() throws BorrowerNotFoundException;
    void saveBorrower(Borrower borrower);
    Optional<Borrower> getBorrowerById(Long id);
    Optional<Borrower> getBorrowerByEmail(String email);
    void deleteBorrowerById(Long borrowerId);

}

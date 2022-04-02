package com.library.services.implementation;


import com.library.models.Borrower;
import com.library.repositories.BorrowerRepository;
import com.library.services.IService.IBorrower;
import com.library.utils.BorrowerNotFoundException;
import com.library.utils.StringManipulator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class BorrowerService implements IBorrower {

    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public Optional<List<Borrower>> retrieveAllBorrower() throws BorrowerNotFoundException {
        Optional<List<Borrower>> borrowers = borrowerRepository.getAllBorrowers();
        borrowers.ifPresentOrElse(ArrayList::new, () -> {
            try {
                throw new BorrowerNotFoundException("No users found");
            } catch (BorrowerNotFoundException e) {
                e.printStackTrace();
            }
        });
        log.warn("Fetching data borrowers");
        return borrowers;
    }

    @Override
    public Optional<Borrower> saveBorrower(Borrower borrower) {
        Optional<Borrower> optionalBorrower = Optional.ofNullable(borrower);

        optionalBorrower.ifPresent(borrowerInfo -> {
            borrowerInfo.setFirstName(StringManipulator.upperEveryStarts(borrower.getFirstName()));
            borrowerInfo.setLastName(StringManipulator.upperEveryStarts(borrower.getLastName()));
            borrowerInfo.setEmailAddress(borrower.getEmailAddress());
            borrowerInfo.setContactNumber(borrower.getContactNumber());
            borrowerInfo.setAddress(borrower.getAddress());
            borrowerInfo.setIsDeleted(false);
            log.warn("Saving {} {}", borrowerInfo.getFirstName(), borrowerInfo.getLastName());
            borrowerRepository.saveAndFlush(borrowerInfo);
        });
        return optionalBorrower;
    }

    @Override
    public Optional<Borrower> getBorrowerById(Long id) {
        log.warn("Fetching borrower with id of {}", id);
        return Optional.ofNullable(borrowerRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new BorrowerNotFoundException("No user found")));
    }

    @Override
    public Optional<Borrower> getBorrowerByEmail(String email) {
        log.warn("Fetching borrower with an email of {}", email);
        return Optional.ofNullable(borrowerRepository.findByEmailAddressAndIsDeletedFalse(email)
                .orElseThrow(() -> new BorrowerNotFoundException("No borrower found with email of" + email)));
    }

    @Override
    public Optional<Borrower> updateBorrower(Borrower borrower) {
        Optional<Borrower> borrowerToUpdate = borrowerRepository.findById(borrower.getId());
        borrowerToUpdate.ifPresent((updatingBorrower)->{
            updatingBorrower.setId(borrower.getId());
            updatingBorrower.setFirstName(StringManipulator.upperEveryStarts(borrower.getFirstName()));
            updatingBorrower.setLastName(StringManipulator.upperEveryStarts(borrower.getLastName()));
            updatingBorrower.setAddress(borrower.getAddress());
            updatingBorrower.setPassword(borrower.getPassword());
            updatingBorrower.setIsDeleted(borrower.getIsDeleted());
        });
        return Optional.of(borrower);
    }

    @Override
    public Optional<Borrower> deleteBorrowerById(Long borrowerId) {
        Optional<Borrower> borrowerToDelete = Optional.ofNullable(getBorrowerById(borrowerId))
                .orElseThrow(() -> new BorrowerNotFoundException("Cannot delete because no borrower found"));
        log.warn("Deleting borrower with an email of {}", borrowerToDelete.map(Borrower::getEmailAddress));
        borrowerToDelete.ifPresent((borrower -> borrower.setIsDeleted(true)));
        return borrowerToDelete;
    }

    @Override
    public Optional<Borrower> retrieveAccount(String email) {
        Optional<Borrower> borrower = borrowerRepository.findByEmailAddressAndIsDeletedTrue(email);
        borrower.ifPresent(activateAccount -> activateAccount.setIsDeleted(false));
        return borrower;
    }
}

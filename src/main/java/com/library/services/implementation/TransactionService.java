package com.library.services.implementation;

import com.library.DAO.UnfinishedTransaction;
import com.library.models.Borrower;
import com.library.models.SpecificBook;
import com.library.models.Transaction;
import com.library.repositories.TransactionRepository;
import com.library.services.IService.ITransaction;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TransactionService implements ITransaction {

    private final TransactionRepository transactionRepository;
    @Lazy
    private final SpecificBookService specificBookService;
    @Lazy
    private final BorrowerService borrowerService;

    public TransactionService(TransactionRepository transactionRepository, SpecificBookService specificBookService, BorrowerService borrowerService) {
        this.transactionRepository = transactionRepository;
        this.specificBookService = specificBookService;
        this.borrowerService = borrowerService;
    }

    @Override
    public void generateTransaction(Long specificBookId, Long borrowerId) {
        Transaction transaction = new Transaction();
        SpecificBook specificBook = specificBookService.getASpecificBook(specificBookId).orElseThrow(null);
        if(!specificBook.getIsBorrowed()){
            specificBook.setIsBorrowed(true);
            specificBook.getBook().setAvailableStocks(specificBook.getBook().getAvailableStocks() -1);
            Borrower borrower = borrowerService.getBorrowerById(borrowerId).orElseThrow(null);
            transaction.setBorrower(borrower);
            transaction.setSpecificBook(specificBook);
            transaction.setDateBorrowed();
            transaction.setDateExpectedReturn();
            transaction.setIsTransactionDone(false);
            log.warn("Generating a transaction...... Borrower : {} Book Title {} {}",
                    borrower.getFirstName(), specificBook.getBook().getTitle(), specificBook.getSpecificBookCode());
            transactionRepository.save(transaction);
        }
        log.warn("You cannot borrow {}  because it is already borrowed", specificBook.getBook().getTitle());
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByBorrower(Long borrowerId) {
        Borrower borrower = borrowerService.getBorrowerById(borrowerId)
                .orElseThrow(()-> new NullPointerException("No user found"));
        return Optional.ofNullable(transactionRepository.findByBorrower(borrower)
                .orElseThrow(() -> new NullPointerException("No Transaction for this user")));
    }

    @Override
    public Optional<List<Transaction>> getUnfinishedTransactions() {
        Optional<List<Transaction>> transactions = transactionRepository.findByIsTransactionDoneFalse();
        transactions.ifPresent(fetchTransactions->{
            fetchTransactions.forEach(fetchTransaction -> {
                UnfinishedTransaction unfinishedTransaction = new UnfinishedTransaction();
                unfinishedTransaction.setTransactionId(fetchTransaction.getId());
                unfinishedTransaction.setDateBorrowed(fetchTransaction.getDateBorrowed());
                unfinishedTransaction.setBookTitle(fetchTransaction.getSpecificBook().getBook().getTitle());
                unfinishedTransaction.setBorrowerName(fetchTransaction.getBorrower().getFirstName());
                unfinishedTransaction.setExpectedDateReturn(fetchTransaction.getDateExpectedReturn());
            });
        });
        return transactions;
    }

    @Override
    public void markTransactionAsDone(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(null);
        if(!transaction.getIsTransactionDone()){
            transaction.getSpecificBook().getBook().setAvailableStocks(transaction.getSpecificBook().getBook().getAvailableStocks() + 1);
            transaction.getSpecificBook().setIsBorrowed(false);
            transaction.setIsTransactionDone(true);
            transaction.setDateBookWasReturn();
            transaction.setStatus(transaction.getDateExpectedReturn());
        }
        log.warn("Transaction with with of {} is already marked done", transactionId);

    }
}

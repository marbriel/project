package com.library.services.IService;

import com.library.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransaction {
    void generateTransaction(Long specificBookId, Long borrowerId);
    Optional<List<Transaction>> getTransactionsByBorrower(Long borrowerId);
    Optional<List<Transaction>> getUnfinishedTransactions();
    void markTransactionAsDone(String transactionId);
}

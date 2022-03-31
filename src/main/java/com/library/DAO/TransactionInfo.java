package com.library.DAO;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionInfo {
    private String transactionId;
    private String bookTitle;
    private String borrowerName;
    private Date dateBorrowed;
    private Date expectedDateReturn;

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "transactionId='" + transactionId + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", dateBorrowed=" + dateBorrowed +
                ", expectedDateReturn=" + expectedDateReturn +
                '}';
    }
}

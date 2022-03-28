package com.library.models;

import com.library.utils.CalendarUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Getter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;



    private Date dateBorrowed;
    private Date dateExpectedReturn;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    @JoinColumn(name = "specific_book_id")
    private SpecificBook specificBook;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

    @Column(columnDefinition = "boolean default false")
    private Boolean isTransactionDone;

    public Transaction(SpecificBook specificBook, Borrower borrower) {
        this.specificBook = specificBook;
        this.borrower = borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public void setSpecificBook(SpecificBook specificBook) {
        this.specificBook = specificBook;
    }

    public void setDateBorrowed(){
        this.dateBorrowed = CalendarUtils.getCurrentDate();
    }

    public void setDateExpectedReturn(){
        this.dateExpectedReturn = CalendarUtils.expectedDateReturn();
    }

    public void setDateExpectedReturn(int days){
        this.dateExpectedReturn = CalendarUtils.expectedDateReturn(days);
    }

}
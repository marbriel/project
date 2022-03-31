package com.library.models;

import com.library.utils.CalendarUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Date dateBookWasReturn;
    private String status;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

    @Column(columnDefinition = "boolean default false")
    @Setter
    private Boolean isTransactionDone;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "specific_book_id")
    private SpecificBook specificBook;

    public void setSpecificBook(SpecificBook specificBook) {
        this.specificBook = specificBook;
    }



    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
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

    public void setDateBookWasReturn(){
        this.dateBookWasReturn = CalendarUtils.getCurrentDate();
    }

    public void setStatus(Date expectedDateReturn){
        if(CalendarUtils.getCurrentDate().before(expectedDateReturn)) {
            this.status = "ON TIME";
        }else {
            this.status = "LATE";
        }
    }




}
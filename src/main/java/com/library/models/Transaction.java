package com.library.models;

import com.library.utils.CalendarUtils;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Getter
public class Transaction {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    private Date dateBorrowed;
    private Date dateExpectedReturn;

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
package com.library.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Getter @Setter
public class Book {

    public Book(String title, String author, Integer availableStocks) {
        this.title = title;
        this.author = author;
        this.availableStocks = availableStocks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String author;
    private Integer availableStocks;
    private List<SpecificBook> specificBookList;

    public void addSpecificBook(SpecificBook specificBook){
        this.specificBookList.add(specificBook);
    }
}
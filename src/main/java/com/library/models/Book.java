package com.library.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String author;
    private Integer availableStocks;

    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            orphanRemoval = true)
    private List<SpecificBook> specificBooks = new ArrayList<>();

    public void addSpecificBook(SpecificBook specificBook){
        this.specificBooks.add(specificBook);
    }

    public Book(String title, String author, Integer availableStocks) {
        this.title = title;
        this.author = author;
        this.availableStocks = availableStocks;
    }
}
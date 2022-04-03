package com.library.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String author;
    private Integer availableStocks;
    private Boolean isOnShelf;

    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            orphanRemoval = true, fetch = LAZY)
    @JsonManagedReference
    private List<SpecificBook> specificBooks = new ArrayList<>();

    public void addSpecificBook(SpecificBook specificBook){
        this.specificBooks.add(specificBook);
    }

    public Book(String title, String author, Integer availableStocks, Boolean isOnShelf) {
        this.title = title;
        this.author = author;
        this.availableStocks = availableStocks;
        this.isOnShelf = isOnShelf;
    }
}
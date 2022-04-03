package com.library.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "specific_book")
@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
@Builder
public class SpecificBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String specificBookCode;

    @Column(columnDefinition = "boolean default false")
    private Boolean isBorrowed;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;

    private Boolean isAvailable;


    public void setSpecificBookCode() {
        this.specificBookCode = UUID.randomUUID().toString();
    }

    public SpecificBook(String specificBookCode, Boolean isBorrowed, Book book, Boolean isAvailable) {
        this.specificBookCode = specificBookCode;
        this.isBorrowed = isBorrowed;
        this.book = book;
        this.isAvailable = isAvailable;
    }
}
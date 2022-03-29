package com.library.models;

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
    private Book book;


    public void setSpecificBookCode() {
        this.specificBookCode = UUID.randomUUID().toString();
    }

    public SpecificBook(String specificBookCode, Boolean isBorrowed) {
        this.specificBookCode = specificBookCode;
        this.isBorrowed = isBorrowed;
    }
}
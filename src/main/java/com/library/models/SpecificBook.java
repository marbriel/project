package com.library.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "specific_book")
@NoArgsConstructor
@Getter @Setter
public class SpecificBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String specificBookCode;

    @Column(columnDefinition = "boolean default false")
    private Boolean isBorrowed;


    public void setSpecificBookCode() {
        this.specificBookCode = UUID.randomUUID().toString();
    }
}
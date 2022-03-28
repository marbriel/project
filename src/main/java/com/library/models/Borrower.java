package com.library.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "borrower")
@NoArgsConstructor
@Getter @Setter
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    @Column(unique = true)
    private String contactNumber;
    @Column(unique = true)
    private String emailAddress;

    public Borrower(String firstName, String lastName, String address, String contactNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
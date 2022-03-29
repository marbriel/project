package com.library.DAO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpecificBookDAO {
    private Long id;
    private String specificBookCode;
    private String bookTitle;
    private Boolean isBorrowed;
    private String status;

    public void setStatus(Boolean ifBorrowed){
        this.status = isBorrowed ? "UNAVAILABLE" : "AVAILABLE";
    }

    @Override
    public String toString() {
        return "SpecificBookDAO{" +
                "id=" + id +
                ", specificBookCode='" + specificBookCode + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", isBorrowed=" + isBorrowed +
                ", status='" + status + '\'' +
                '}';
    }
}

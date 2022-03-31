package com.library.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FinishedTransaction extends TransactionInfo{
    private Date bookReturn;
    private String status;

    @Override
    public String toString() {
        return "FinishedTransaction{" +
                "bookReturn=" + bookReturn +
                ", status='" + status + '\'' +
                '}';
    }
}

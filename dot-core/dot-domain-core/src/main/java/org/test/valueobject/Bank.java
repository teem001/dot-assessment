package org.test.valueobject;

import lombok.Getter;
import org.test.exception.TransactionException;

@Getter
public class Bank {

    private final String bankName;
    private final String sortCode;
    private final String bankCode;

    public Bank(String bankName, String sortCode, String bankCode) {
        this.bankName = bankName;
        this.sortCode = sortCode;
        this.bankCode = bankCode;
    }

    public void verify() {
        if(bankName == null || sortCode == null) throw new TransactionException( " Incomplete bank details !!!");
    }
}

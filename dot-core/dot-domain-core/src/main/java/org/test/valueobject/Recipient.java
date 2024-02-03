package org.test.valueobject;

import lombok.Getter;
import org.test.exception.TransactionException;

@Getter
public class Recipient {

    private final String name;
    private final String accountNumber;
    private final Bank bank;

    public Recipient(String name, String accountNumber, Bank bank) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.bank = bank;
    }

    public void verify(){
        validateName();
        validateAccountNumber();
        validateBank();
    }

    private void validateBank() {
        bank.verify();
    }

    private void validateAccountNumber() {
        if(accountNumber == null) throw  new TransactionException( "Recipient account number can not be empty!!!");
    }

    private void validateName() {
        if(name == null) throw new TransactionException("Recipient name can not be empty");
    }

}

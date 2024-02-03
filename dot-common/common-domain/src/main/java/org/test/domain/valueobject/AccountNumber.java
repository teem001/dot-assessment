package org.test.domain.valueobject;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class AccountNumber {

    private final String accountNumber;
    private final LocalDateTime createdDate = LocalDateTime.now() ;

    public AccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountNumber that = (AccountNumber) o;
        return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, createdDate);
    }

    @Override
    public String toString() {
        return "AccountNumber{" +
                "accountNumber=" + accountNumber +
                ", createdDate=" + createdDate +
                '}';
    }


    public static AccountNumber generate(String accountNumber){
        return  new AccountNumber(accountNumber);
    }
}

package org.test.domain;

import lombok.Getter;
import lombok.Setter;
import org.test.domain.entity.AggregateRoot;
import org.test.domain.valueobject.AccountId;
import org.test.domain.valueobject.AccountNumber;
import org.test.domain.valueobject.AccountStatus;
import org.test.domain.valueobject.Money;
import org.test.domain.valueobject.UserId;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Account extends AggregateRoot<AccountId> {


    private final UserId userId;
    private final AccountNumber accountNumber;
    private final Money accountBalance;

    private final AccountStatus accountStatus;
    @Setter
    private List<String> accountStatusMessage;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", accountNumber=" + accountNumber +
                ", accountBalance=" + accountBalance +
                ", accountStatus=" + accountStatus +
                ", accountStatusMessage=" + accountStatusMessage +
                ", createdAt=" + createdAt +
                '}';
    }

    public Account(UserId userId, AccountNumber accountNumber, Money accountBalance, AccountStatus accountStatus) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountStatus = accountStatus;
    }
}

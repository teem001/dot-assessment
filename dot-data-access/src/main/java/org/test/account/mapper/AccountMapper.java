package org.test.account.mapper;

import org.springframework.stereotype.Component;
import org.test.account.entity.AccountEntity;
import org.test.domain.Account;
import org.test.domain.valueobject.AccountId;
import org.test.domain.valueobject.AccountNumber;
import org.test.domain.valueobject.Money;
import org.test.domain.valueobject.UserId;

@Component
public class AccountMapper {
    public Account accountEntityToAccount(AccountEntity entity) {
        var account = new Account(new UserId(entity.getUserId()), new AccountNumber(entity.getAccountNumber()), new Money(entity.getAccountBalance()), entity.getAccountStatus());
        account.setId(new AccountId(entity.getId()));
        return account;
    }
}

package org.test.port.output;

import org.test.domain.Account;
import org.test.domain.valueobject.AccountId;
import org.test.domain.valueobject.UserId;

import javax.security.auth.login.AccountNotFoundException;

public interface AccountRepository {

    Account findAccountById(AccountId accountId) throws AccountNotFoundException;

    Account findAccountByUserId(UserId userId);
}

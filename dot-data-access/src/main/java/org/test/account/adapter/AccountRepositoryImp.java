package org.test.account.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.account.entity.AccountEntity;
import org.test.account.mapper.AccountMapper;
import org.test.domain.Account;
import org.test.domain.valueobject.AccountId;
import org.test.domain.valueobject.AccountStatus;
import org.test.domain.valueobject.UserId;
import org.test.port.output.AccountRepository;
import org.test.transaction.repository.AccountJPARepository;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountRepositoryImp implements AccountRepository {

    private final AccountJPARepository accountJPARepository;
    private final AccountMapper accountMapper;

    @Override
    public Account findAccountById(AccountId accountId) throws AccountNotFoundException {

        var accountEntity = accountJPARepository.findById(accountId.getValue())
                .orElse( new AccountEntity(UUID.fromString("bd7929bc-8dae-4133-9ced-4162f6e4aede"),UUID.fromString("bd7929bc-8dae-4133-9ced-4162f6e4aede"),
                        "Ola ola", "1234567890", new BigDecimal("10000000"), AccountStatus.UNLOCKED,"active no error"));

        return accountMapper.accountEntityToAccount(accountEntity);
    }

    @Override
    public Account findAccountByUserId(UserId userId) {
        return null;
    }
}

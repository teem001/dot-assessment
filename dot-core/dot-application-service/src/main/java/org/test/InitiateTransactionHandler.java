package org.test;

import org.springframework.stereotype.Component;
import org.test.domain.Account;
import org.test.domain.Commission;
import org.test.domain.Transaction;
import org.test.domain.factory.HandlerFactory;
import org.test.domain.valueobject.AccountId;
import org.test.domain.valueobject.CommissionType;
import org.test.domain.valueobject.Money;
import org.test.dto.transaction.CreateTransactionCommand;
import org.test.dto.transaction.CreateTransactionResponse;
import org.test.mapper.TransactionObjectMapper;
import org.test.port.output.AccountRepository;
import org.test.port.output.TransactionRepository;
import org.test.valueobject.Bank;
import org.test.valueobject.Recipient;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@Component
public class InitiateTransactionHandler implements HandlerFactory<CreateTransactionResponse, CreateTransactionCommand> {

    private final AccountRepository accountRepository;


    private final TransactionRepository transactionRepository;
    private final TransactionObjectMapper transactionObjectMapper;

    public InitiateTransactionHandler(AccountRepository accountRepository, TransactionRepository transactionRepository, TransactionObjectMapper transactionObjectMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionObjectMapper = transactionObjectMapper;
    }


    public CreateTransactionResponse handle(CreateTransactionCommand createTransactionCommand) throws AccountNotFoundException {

        Account account = accountRepository.findAccountById(new AccountId(createTransactionCommand.accountId()));

        var commission = new Commission(new Money(createTransactionCommand.amount()));

        commission.setCommissionType(CommissionType.PERCENTAGE);
        commission.setValue(5.00);
        commission.setCapLimit(new Money(new BigDecimal("100")));


        Transaction transaction = transactionRepository.initiateTransaction(new Transaction(account.getId(), account.getAccountStatus(),
                new Recipient(createTransactionCommand.fromAccountName(), createTransactionCommand.fromAccountNumber(), new Bank(createTransactionCommand.fromBankName(), createTransactionCommand.fromBankSortCode(), createTransactionCommand.fromBankCode())),
                account.getAccountBalance(), new Recipient(createTransactionCommand.toAccountName(),
                createTransactionCommand.toAccountNumber(), new Bank(createTransactionCommand.toBankName(),
                createTransactionCommand.toBankSortCode(), createTransactionCommand.toBankCode())),
                new Money(createTransactionCommand.amount()), commission.getCommissionAmount(), createTransactionCommand.transactionDetails()));

        return transactionObjectMapper.transactionToCreateTransactionResponse(transaction);


    }
}

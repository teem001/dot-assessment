package org.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.domain.Account;
import org.test.domain.Commission;
import org.test.domain.Transaction;
import org.test.domain.factory.HandlerFactory;
import org.test.domain.valueobject.AccountId;
import org.test.domain.valueobject.AccountNumber;
import org.test.domain.valueobject.AccountStatus;
import org.test.domain.valueobject.CommissionId;
import org.test.domain.valueobject.CommissionType;
import org.test.domain.valueobject.Money;
import org.test.domain.valueobject.TransactionEventStatus;
import org.test.domain.valueobject.UserId;
import org.test.dto.transaction.CreateTransactionCommand;
import org.test.dto.transaction.CreateTransactionResponse;
import org.test.mapper.TransactionObjectMapper;
import org.test.port.input.TransactionApplicationService;
import org.test.port.output.AccountRepository;
import org.test.port.output.TransactionRepository;
import org.test.transaction.adapter.TransactionJPARepositoryImp;
import org.test.transaction.entity.TransactionEntity;
import org.test.transaction.mapper.TransactionMapper;
import org.test.transaction.repository.TransactionJPARepository;
import org.test.valueobject.Bank;
import org.test.valueobject.Recipient;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionTest {

    @Mock
    private TransactionApplicationService transactionApplicationService;

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionJPARepositoryImp transactionJPARepositoryImp;


    @InjectMocks
    private HandlerFactory<CreateTransactionResponse, CreateTransactionCommand> handlerFactory = Mockito.mock(InitiateTransactionHandler.class);

    @InjectMocks
    private TransactionObjectMapper transactionObjectMapper;
    @Mock
    private TransactionJPARepository jpaRepository;
    private CreateTransactionCommand createTransactionCommandForFailInput;

    private final UUID transactionId = UUID.fromString("efcf76b6-67c9-4b6f-b487-c7e3a5a85ccf");
    private static final UUID accountId = UUID.fromString("a5b484c6-8c1a-40c0-9427-805e6ab1735c");
    private static final UUID userId = UUID.fromString("bd7929bc-8dae-4133-9ced-4162f6e4aede");

    private static final BigDecimal amount = new BigDecimal("5000.00");
    private static final String fromBankName = "ACCESS";
    private static final String fromAccountNumber = "1234567890";
    private static final String fromAccountName ="Ola Femi";
    private static final String fromBankSortCode = "22222";
    private static final String toBankName = "ACCESS";
    private static final String toAccountNumber = "1234567890";
    private static final String toAccountName ="Ola Femi";
    private static final String toBankSortCode = "22222";
    private static final String transactionDetails = "Transaction test";
    private static final BigDecimal accBalance = new BigDecimal("80000");
    private static final CommissionType commissionType = CommissionType.PERCENTAGE;

    static Account account;

    @BeforeAll
    public static void init(){

    }


    @Test
    public void initializeTransaction() throws AccountNotFoundException {
        CreateTransactionCommand createTransactionCommandForSuccessInput = new CreateTransactionCommand(accountId, amount, fromBankName,
                fromAccountNumber, fromBankSortCode, fromAccountName, "", toBankName, toAccountNumber, toBankSortCode, "",
                toAccountName, transactionDetails);

        account = new Account(new UserId(userId),new AccountNumber(toAccountNumber), new Money(accBalance), AccountStatus.UNLOCKED  );
        account.setId(new AccountId(accountId));

        Commission commission = new Commission(new Money(amount));
        commission.setId(new CommissionId(1L));
        commission.setCommissionType(commissionType);
        commission.setValue(5D);
        commission.setCapLimit(new Money(new BigDecimal(100)));

//        given(accountRepository.findAccountByUserId(new UserId(userId))).willReturn(account);
//        given(accountRepository.findAccountById(new AccountId(accountId))).willReturn(account);
//        when(transactionRepository.initiateTransaction(any())).thenReturn();

//        given(transactionRepository.initiateTransaction());

       var value = handlerFactory.handle(createTransactionCommandForSuccessInput);
       assertThat(value.getStatus()).isEqualTo(TransactionEventStatus.PENDING);
    }


    @Test
    public void testInitiateTransaction() {
        // Create a sample Transaction
        Commission commission = new Commission(new Money(amount));
        commission.setId(new CommissionId(1L));
        commission.setCommissionType(commissionType);
        commission.setValue(5D);
        commission.setCapLimit(new Money(new BigDecimal(100)));

        Transaction transaction =  new Transaction(new AccountId(accountId), AccountStatus.UNLOCKED, new Recipient(fromAccountName, fromAccountNumber, new Bank(fromBankName, fromBankSortCode, ""))
                ,new Money(accBalance),new Recipient(toAccountName, toAccountNumber, new Bank(toBankName, toBankSortCode, "")),
                new Money(amount), commission.getCommission(),"" );

        // Create a sample TransactionEntity
        TransactionEntity transactionEntity = transactionMapper.transactionToTransactionEntity(transaction);

        // Mock behavior of transactionMapper
//        when(transactionMapper.transactionToTransactionEntity(transaction)).thenReturn(transactionEntity);

        // Mock behavior of transactionJPARepository
//        when(transactionJPARepository.save(transactionEntity)).thenReturn(transactionEntity);

        // Call the initiateTransaction method
//        transactionJPARepositoryImp.initiateTransaction(transaction);
        verify(jpaRepository).save(transactionEntity);


        assertEquals(transaction.getTransactionEventStatus(), TransactionEventStatus.INITIALIZE);
    }


}

package org.test.transaction.mapper;

import org.springframework.stereotype.Component;
import org.test.domain.Transaction;
import org.test.domain.valueobject.AccountId;
import org.test.domain.valueobject.Money;
import org.test.domain.valueobject.TransactionId;
import org.test.transaction.entity.TransactionEntity;
import org.test.valueobject.Bank;
import org.test.valueobject.Recipient;

import java.util.UUID;

@Component
public class TransactionMapper {
    public Transaction transactionEntityToTransaction(TransactionEntity entity) {

        var transaction = new Transaction(new AccountId(entity.getSubscriberId()), entity.getAccountStatus(),
                new Recipient(entity.getActorName(), entity.getActorAccountNumber(), new Bank(entity.getActorBank(), entity.getActorBankSortCode(), entity.getActorBankCode())),
                new Money(entity.getAccountBalanceBefore()), new Recipient(entity.getRecipientName(), entity.getRecipientAccountNumber(),
                new Bank(entity.getRecipientBankName(), entity.getRecipientBankSortCode(), entity.getActorBankCode())),
                new Money(entity.getAmount()), new Money(entity.getCommission()),
                entity.getTransactionDetails(), entity.getTransactionStatus().toString(), entity.getPaymentStatus()
        );

        transaction.setId(new TransactionId(entity.getId()));
        return transaction;
    }

    public Transaction transactionEntityToTransaction(TransactionEntity entity, Boolean isApprove) {

        var transaction = new Transaction(new AccountId(entity.getSubscriberId()), entity.getAccountStatus(),
                new Recipient(entity.getActorName(), entity.getActorAccountNumber(), new Bank(entity.getActorBank(), entity.getActorBankSortCode(), entity.getActorBankCode())),
                new Money(entity.getAccountBalanceBefore()), new Recipient(entity.getRecipientName(), entity.getRecipientAccountNumber(),
                new Bank(entity.getRecipientBankName(), entity.getRecipientBankSortCode(), entity.getActorBankCode())),
                new Money(entity.getAmount()), new Money(entity.getCommission()),
                entity.getTransactionDetails(), entity.getTransactionStatus().toString(), isApprove, entity.getTransactionStatus(), entity.getPaymentStatus()
        );

        transaction.setId(new TransactionId(entity.getId()));
        return transaction;
    }

    public TransactionEntity transactionToTransactionEntity(Transaction transaction) {

        return new TransactionEntity(UUID.randomUUID(),transaction.getPayee().getAccountNumber(), transaction.getAccountStatus(), transaction.getPayee().getName(),
                transaction.getPayee().getBank().getBankName(), transaction.getPayee().getBank().getBankCode(),transaction.getPayee().getBank().getBankCode(),
                transaction.getTransactionAmount().getAmount(),transaction.getCommission().getAmount(), transaction.getTimeOfInstance(),
                transaction.getTransactionEventStatus(),transaction.getTransactionDetails(),
                transaction.getPayee().getName(), transaction.getPayee().getAccountNumber(),
                transaction.getPayee().getBank().getSortCode(), transaction.getPayee().getBank().getBankCode(),
                transaction.getPayee().getBank().getBankName(), transaction.getTransactionDetails(), transaction.getAccount().getValue(), transaction.getPaymentStatus());
    }

}

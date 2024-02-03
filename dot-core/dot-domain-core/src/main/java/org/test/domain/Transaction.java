package org.test.domain;

import lombok.Getter;
import org.test.domain.entity.AggregateRoot;
import org.test.domain.valueobject.AccountId;
import org.test.domain.valueobject.AccountStatus;
import org.test.domain.valueobject.Money;
import org.test.domain.valueobject.PaymentStatus;
import org.test.domain.valueobject.TransactionEventStatus;
import org.test.domain.valueobject.TransactionId;
import org.test.exception.TransactionException;
import org.test.valueobject.Recipient;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Transaction extends AggregateRoot<TransactionId> {

    private final AccountId account;
    private final AccountStatus accountStatus;
    private final Recipient payer;
    private final Money accountBalance;
    private final Recipient payee;
    private final Money transactionAmount;
    private final Money commission;
    private final String transactionDetails;
    private TransactionEventStatus transactionEventStatus;
    private PaymentStatus paymentStatus;
    private String statusMessage;
    private Boolean isApproved;
    private final LocalDateTime timeOfInstance = LocalDateTime.now();

    public Transaction(AccountId accountId, AccountStatus accountStatus, Recipient payer,
                       Money accountBalance, Recipient payee, Money transactionAmount,
                       Money commission, String transactionDetails, String statusMessage, PaymentStatus paymentStatus) {
        this.account = accountId;
        this.accountStatus = accountStatus;
        this.payer = payer;
        this.accountBalance = accountBalance;
        this.payee = payee;
        this.transactionAmount = transactionAmount;
        this.commission = commission;
        this.transactionDetails = transactionDetails;
        this.statusMessage = statusMessage;
        this.paymentStatus = paymentStatus;
    }
    public Transaction(AccountId accountId, AccountStatus accountStatus, Recipient payer, Money accountBalance, Recipient payee,
                       Money transactionAmount, Money commission, String transactionDetails, String statusMessage,
                       Boolean isApproved, TransactionEventStatus transactionEventStatus, PaymentStatus paymentStatus) {
        this.account = accountId;
        this.accountStatus = accountStatus;
        this.payer = payer;
        this.accountBalance = accountBalance;
        this.payee = payee;
        this.transactionAmount = transactionAmount;
        this.commission = commission;
        this.transactionDetails = transactionDetails;
        this.statusMessage = statusMessage;
        this.isApproved = isApproved;
        this.transactionEventStatus = transactionEventStatus;
        this.paymentStatus = paymentStatus;
    }

    public Transaction(AccountId account, AccountStatus accountStatus, Recipient payer, Money accountBalance,
                       Recipient payee, Money transactionAmount, Money commission, String transactionDetails) {
        this.account = account;
        this.accountStatus = accountStatus;
        this.payer = payer;
        this.accountBalance = accountBalance;
        this.payee = payee;
        this.transactionAmount = transactionAmount;
        this.commission = commission;
        this.transactionDetails = transactionDetails;
    }

    public void initialiseTransaction() {

        if (account == null) {
            statusMessage = "Subscriber Access declined";
            transactionEventStatus = TransactionEventStatus.DECLINED;
            paymentStatus = PaymentStatus.DECLINED;
            throw new TransactionException("No payment account found for this transaction");
        }



        if(accountStatus != AccountStatus.UNLOCKED){
            statusMessage = "Subscriber Access locked";
            transactionEventStatus = TransactionEventStatus.DECLINED;
            paymentStatus = PaymentStatus.DECLINED;
            throw new TransactionException("Account can not perform this operation");
        }

        if (getId() != null){
            statusMessage = "Existing transaction can not be initialize!!!";
            transactionEventStatus = TransactionEventStatus.DECLINED;
            paymentStatus = PaymentStatus.DECLINED;
            throw new TransactionException("Existing transaction can not be initialize!!!");
        }

        if (transactionEventStatus != null){

            statusMessage = "Existing transaction can not be initialize!!!";
            transactionEventStatus = TransactionEventStatus.DECLINED;
            paymentStatus = PaymentStatus.DECLINED;

            throw new TransactionException("Failed initializing transaction with transaction status " + transactionEventStatus);
        }

        if (paymentStatus != null){
            statusMessage = "Existing transaction can not be initialize!!!";
            transactionEventStatus = TransactionEventStatus.DECLINED;
            paymentStatus = PaymentStatus.DECLINED;
            throw new TransactionException("Failed initializing transaction with payment status" + paymentStatus);
        }



        if (transactionAmount.add(transactionAmount).isGreaterThan(accountBalance)) {
            statusMessage = "Transaction declined. insufficient fund";
            paymentStatus = PaymentStatus.DECLINED;
            transactionEventStatus = TransactionEventStatus.DECLINED;
            throw new TransactionException("Insufficient balance");
        }

        payee.verify();
        payer.verify();

        this.transactionEventStatus = TransactionEventStatus.INITIALIZE;
        this.paymentStatus = PaymentStatus.PENDING;
        this.isApproved = false;
    }

    public void processTransaction() {

        List<TransactionEventStatus> notProcessingStatus = List.of(TransactionEventStatus.PENDING, TransactionEventStatus.CANCELLED, TransactionEventStatus.COMPLETED, TransactionEventStatus.DECLINED);

        if (notProcessingStatus.contains(transactionEventStatus))
            throw new TransactionException("Transaction status not valid for processing");

        if (getId() == null) throw new TransactionException("Transaction has not been initialized");

        if(!isApproved) throw new TransactionException("Transaction not approved for processing");

        transactionEventStatus = TransactionEventStatus.PENDING;
    }

    public void declineTransaction() {
        if (getId() == null) throw new TransactionException(" Transaction has not been initialized");
        if (paymentStatus != PaymentStatus.PENDING)
            throw new TransactionException("This transaction payment has been terminated");
        if (transactionEventStatus != TransactionEventStatus.PENDING)
            throw new TransactionException("This transaction has terminal event");
        paymentStatus = PaymentStatus.DECLINED;
        transactionEventStatus = TransactionEventStatus.DECLINED;

    }

    public void completeTransaction() {
        if (getId() == null) throw new TransactionException("Transaction has not been initialized");

        if (paymentStatus == PaymentStatus.PENDING )
            throw new TransactionException("This transaction payment status has pending status. transaction has not been processed");
        if (transactionEventStatus != TransactionEventStatus.PENDING)
            throw new TransactionException("This transaction has terminal event");
        transactionEventStatus = TransactionEventStatus.COMPLETED;
        isApproved = true;

    }

    public void cancelTransaction() {
        if (paymentStatus != PaymentStatus.PENDING)
            throw new TransactionException("This transaction payment has been terminated");
        if (transactionEventStatus != TransactionEventStatus.PENDING)
            throw new TransactionException("This transaction has terminal event");
        paymentStatus = PaymentStatus.CANCELLED;

    }


}

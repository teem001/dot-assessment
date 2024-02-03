package org.test.dto.transaction;

import lombok.Getter;
import org.test.domain.valueobject.TransactionEventStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class CreateTransactionResponse {

    private final UUID transactionId;
    private final BigDecimal totalAmount;

    private final BigDecimal amount;
    private final BigDecimal commission;

    private final String name;
    private final String accountNumber;
    private final String bankName;

    private final TransactionEventStatus status;

    private String mesage;


    public CreateTransactionResponse(UUID transactionId, BigDecimal totalAmount, BigDecimal amount, BigDecimal commission, String name, String accountNumber, String bankName, TransactionEventStatus status) {
        this.transactionId = transactionId;
        this.totalAmount = totalAmount;
        this.amount = amount;
        this.commission = commission;
        this.name = name;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.status = status;
    }

    public CreateTransactionResponse(UUID transactionId, BigDecimal totalAmount, BigDecimal amount, BigDecimal commission, String name, String accountNumber, String bankName, TransactionEventStatus status, String errorMessage) {
        this.transactionId = transactionId;
        this.totalAmount = totalAmount;
        this.amount = amount;
        this.commission = commission;
        this.name = name;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.status = status;
        this.mesage = errorMessage;
    }
}

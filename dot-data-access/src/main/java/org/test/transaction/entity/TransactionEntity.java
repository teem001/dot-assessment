package org.test.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.test.domain.valueobject.AccountStatus;
import org.test.domain.valueobject.PaymentStatus;
import org.test.domain.valueobject.TransactionEventStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
@Data
public class TransactionEntity {

    @Id
    private  UUID id;
    private String actorAccountNumber;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private String actorName;
    private String actorBank;
    private String actorBankSortCode;
    private String actorBankCode;
    private BigDecimal amount;
    private BigDecimal accountBalanceBefore;
    private BigDecimal accountBalanceAfter;
    private BigDecimal commission;
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    private TransactionEventStatus transactionStatus;
    private String statusDescription;
    private String recipientName;
    private String recipientAccountNumber;
    private String recipientBankSortCode;
    private String recipientBankCode;
    private String recipientBankName;
    private String transactionDetails;
    private UUID subscriberId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


    public TransactionEntity(String actorAccountNumber, AccountStatus accountStatus, String actorName, String actorBank, String actorBankSortCode,
                             String actorBankCode, BigDecimal amount, BigDecimal accountBalanceBefore, BigDecimal accountBalanceAfter,
                             BigDecimal commission, LocalDateTime transactionDate, TransactionEventStatus transactionStatus,
                             String statusDescription, String recipientName, String recipientAccountNumber, String recipientBankSortCode,
                             String recipientBankCode, String recipientBankName, String transactionDetails, UUID subscriberId) {
        this.actorAccountNumber = actorAccountNumber;
        this.accountStatus = accountStatus;
        this.actorName = actorName;
        this.actorBank = actorBank;
        this.actorBankSortCode = actorBankSortCode;
        this.actorBankCode = actorBankCode;
        this.amount = amount;
        this.accountBalanceBefore = accountBalanceBefore;
        this.accountBalanceAfter = accountBalanceAfter;
        this.commission = commission;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.statusDescription = statusDescription;
        this.recipientName = recipientName;
        this.recipientAccountNumber = recipientAccountNumber;
        this.recipientBankSortCode = recipientBankSortCode;
        this.recipientBankCode = recipientBankCode;
        this.recipientBankName = recipientBankName;
        this.transactionDetails = transactionDetails;
        this.subscriberId = subscriberId;
    }

    public TransactionEntity(UUID id, String actorAccountNumber, AccountStatus accountStatus, String actorName, String actorBank,
                             String actorBankSortCode, String actorBankCode, BigDecimal amount, BigDecimal commission, LocalDateTime transactionDate,
                             TransactionEventStatus transactionStatus, String statusDescription, String recipientName, String recipientAccountNumber,
                             String recipientBankSortCode, String recipientBankCode, String recipientBankName, String transactionDetails, UUID subscriberId, PaymentStatus paymentStatus
) {
        this.id = id;
        this.accountStatus =accountStatus;
        this.actorAccountNumber = actorAccountNumber;
        this.commission = commission;
        this.actorName = actorName;
        this.actorBank = actorBank;
        this.actorBankSortCode = actorBankSortCode;
        this.actorBankCode = actorBankCode;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.statusDescription = statusDescription;
        this.recipientName = recipientName;
        this.recipientAccountNumber = recipientAccountNumber;
        this.recipientBankSortCode = recipientBankSortCode;
        this.recipientBankCode = recipientBankCode;
        this.recipientBankName = recipientBankName;
        this.transactionDetails = transactionDetails;
        this.subscriberId = subscriberId;
        this.paymentStatus = paymentStatus;

    }
}

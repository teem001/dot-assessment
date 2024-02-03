package org.test.dto.transaction;

import org.test.domain.valueobject.PaymentStatus;
import org.test.domain.valueobject.TransactionId;


public record CompleteTransactionResponse(TransactionId transactionId, PaymentStatus paymentStatus, String message) {

}

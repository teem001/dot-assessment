package org.test.dto.transaction;

import org.test.domain.valueobject.PaymentStatus;


public record ProcessTransactionResponse(PaymentStatus paymentStatus, String message) {

}

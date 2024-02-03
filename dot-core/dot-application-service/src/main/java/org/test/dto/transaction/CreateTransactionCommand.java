package org.test.dto.transaction;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionCommand(UUID accountId, BigDecimal amount, String fromBankName,
                                       String fromAccountNumber, String fromBankSortCode, String fromAccountName, String fromBankCode,
                                       String toBankName, String toAccountNumber, String toBankSortCode, String toBankCode,
                                       String toAccountName, String transactionDetails) {

}

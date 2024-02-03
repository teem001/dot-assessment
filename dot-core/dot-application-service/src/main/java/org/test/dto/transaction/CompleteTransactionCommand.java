package org.test.dto.transaction;

import java.util.UUID;


public record CompleteTransactionCommand(UUID transactionId)  {
}

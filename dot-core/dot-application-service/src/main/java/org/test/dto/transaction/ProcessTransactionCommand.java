package org.test.dto.transaction;

import org.test.valueobject.UserCredential;

import java.util.List;
import java.util.UUID;

public record ProcessTransactionCommand(UUID transactionId, List<UserCredential> userCredential) {


}

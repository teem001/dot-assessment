package org.test.port.output;

import org.test.domain.Transaction;
import org.test.domain.valueobject.TransactionId;

public interface TransactionRepository {

    Transaction initiateTransaction(Transaction transaction);

    Transaction processTransaction(TransactionId transactionId);

    Transaction confirmTransaction(TransactionId transactionId);

}

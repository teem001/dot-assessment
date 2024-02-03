package org.test.exception;

import org.test.domain.exception.DotTransactionException;


public class TransactionException extends DotTransactionException {
    public TransactionException(String s) {
        super(s);
    }

}

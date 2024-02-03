package org.test.mapper;

import org.springframework.stereotype.Component;
import org.test.domain.Transaction;
import org.test.dto.transaction.CreateTransactionResponse;
import org.test.dto.transaction.ProcessTransactionResponse;

@Component
public class TransactionObjectMapper {


    public CreateTransactionResponse transactionToCreateTransactionResponse(Transaction transaction) {


        return new CreateTransactionResponse(transaction.getId().getValue(),transaction.getTransactionAmount().add(transaction.getCommission()).getAmount(),
                transaction.getTransactionAmount().getAmount(), transaction.getCommission().getAmount(), transaction.getPayee().getName(),
                transaction.getPayee().getAccountNumber(), transaction.getPayee().getBank().getBankName(), transaction.getTransactionEventStatus(),
                transaction.getStatusMessage() );
    }

    public ProcessTransactionResponse transactionToProcessTransactionResponse(Transaction transaction) {

        return new ProcessTransactionResponse(transaction.getPaymentStatus(), "Transaction processing");
    }
}

package org.test;

import org.springframework.stereotype.Component;
import org.test.domain.factory.HandlerFactory;
import org.test.domain.valueobject.TransactionId;
import org.test.dto.transaction.CompleteTransactionCommand;
import org.test.dto.transaction.CompleteTransactionResponse;
import org.test.mapper.TransactionObjectMapper;
import org.test.port.output.TransactionRepository;

@Component
public class CompleteTransactionHandler implements HandlerFactory<CompleteTransactionResponse, CompleteTransactionCommand> {

    private final TransactionRepository transactionRepository;
    private final TransactionObjectMapper transactionObjectMapper;

    public CompleteTransactionHandler(TransactionRepository transactionRepository, TransactionObjectMapper transactionObjectMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionObjectMapper = transactionObjectMapper;
    }


    @Override
    public CompleteTransactionResponse handle(CompleteTransactionCommand completeTransactionCommand) {

        var transaction = transactionRepository.confirmTransaction(new TransactionId(completeTransactionCommand.transactionId()));

        transaction.completeTransaction();
        return null;
    }

}

package org.test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.domain.factory.HandlerFactory;
import org.test.domain.valueobject.TransactionId;
import org.test.dto.transaction.ProcessTransactionCommand;
import org.test.dto.transaction.ProcessTransactionResponse;
import org.test.mapper.TransactionObjectMapper;
import org.test.port.output.AccountRepository;
import org.test.port.output.TransactionRepository;

@Component
@AllArgsConstructor
public class ProcessTransactionHandler implements HandlerFactory<ProcessTransactionResponse, ProcessTransactionCommand> {

    private final AccountRepository accountRepository;


    private final TransactionRepository transactionRepository;
    private final TransactionObjectMapper transactionObjectMapper;

    @Override
    public ProcessTransactionResponse handle(ProcessTransactionCommand processTransactionCommand) {

        var transaction = transactionRepository.processTransaction(new TransactionId(processTransactionCommand.transactionId()));

        return transactionObjectMapper.transactionToProcessTransactionResponse(transaction);


    }
}

package org.test;

import org.springframework.stereotype.Service;
import org.test.dto.transaction.CompleteTransactionCommand;
import org.test.dto.transaction.CompleteTransactionResponse;
import org.test.dto.transaction.CreateTransactionCommand;
import org.test.dto.transaction.CreateTransactionResponse;
import org.test.dto.transaction.ProcessTransactionCommand;
import org.test.dto.transaction.ProcessTransactionResponse;
import org.test.port.input.TransactionApplicationService;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class TransactionApplicationServiceImp implements TransactionApplicationService {

    private final InitiateTransactionHandler initiateTransactionHandler;
    private final ProcessTransactionHandler processTransactionHandler;

    private final CompleteTransactionHandler completeTransactionHandler;

    public TransactionApplicationServiceImp(InitiateTransactionHandler initiateTransactionHandler, ProcessTransactionHandler processTransactionHandler, CompleteTransactionHandler completeTransactionHandler) {
        this.initiateTransactionHandler = initiateTransactionHandler;
        this.processTransactionHandler = processTransactionHandler;
        this.completeTransactionHandler = completeTransactionHandler;
    }


    @Override
    public CreateTransactionResponse initiateTransaction(CreateTransactionCommand createTransactionCommand) throws AccountNotFoundException {
        return initiateTransactionHandler.handle(createTransactionCommand);
    }

    @Override
    public ProcessTransactionResponse processTransaction(ProcessTransactionCommand processTransactionCommand) {
        return processTransactionHandler.handle(processTransactionCommand);
    }

    @Override
    public CompleteTransactionResponse completeTransaction(CompleteTransactionCommand completeTransactionCommand) {
        return completeTransactionHandler.handle(completeTransactionCommand);
    }
}

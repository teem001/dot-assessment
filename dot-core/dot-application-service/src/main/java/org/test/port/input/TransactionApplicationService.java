package org.test.port.input;

import org.test.dto.transaction.CompleteTransactionCommand;
import org.test.dto.transaction.CompleteTransactionResponse;
import org.test.dto.transaction.CreateTransactionCommand;
import org.test.dto.transaction.CreateTransactionResponse;
import org.test.dto.transaction.ProcessTransactionCommand;
import org.test.dto.transaction.ProcessTransactionResponse;

import javax.security.auth.login.AccountNotFoundException;

public interface TransactionApplicationService {


    CreateTransactionResponse initiateTransaction(CreateTransactionCommand createTransactionCommand) throws AccountNotFoundException;
    ProcessTransactionResponse processTransaction(ProcessTransactionCommand processTransactionCommand);

    CompleteTransactionResponse completeTransaction(CompleteTransactionCommand completeTransactionCommand);

}

package org.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.dto.transaction.CreateTransactionCommand;
import org.test.dto.transaction.CreateTransactionResponse;
import org.test.dto.transaction.ProcessTransactionCommand;
import org.test.dto.transaction.ProcessTransactionResponse;
import org.test.port.input.TransactionApplicationService;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("api/v1/transaction")
@AllArgsConstructor
public class DotController {

    private final TransactionApplicationService transactionApplicationService;


    @PostMapping("/initiate")
    public ResponseEntity<CreateTransactionResponse> createTransaction(@RequestBody CreateTransactionCommand createTransactionCommand) throws AccountNotFoundException {

        return ResponseEntity.ok(transactionApplicationService.initiateTransaction(createTransactionCommand));
    }

    @PostMapping ("/process")
    public ResponseEntity<ProcessTransactionResponse> processTransaction(@RequestBody ProcessTransactionCommand processTransactionCommand){

        return ResponseEntity.ok(transactionApplicationService.processTransaction(processTransactionCommand));
    }


    
    
}

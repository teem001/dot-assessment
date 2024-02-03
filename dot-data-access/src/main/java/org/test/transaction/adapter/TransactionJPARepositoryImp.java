package org.test.transaction.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.domain.Transaction;
import org.test.domain.valueobject.TransactionId;
import org.test.exception.TransactionException;
import org.test.port.output.TransactionRepository;
import org.test.transaction.entity.TransactionEntity;
import org.test.transaction.mapper.TransactionMapper;
import org.test.transaction.repository.TransactionJPARepository;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class TransactionJPARepositoryImp implements TransactionRepository {
    private final TransactionJPARepository transactionJPARepository;
    private final TransactionMapper transactionMapper;


    public TransactionJPARepositoryImp(TransactionJPARepository transactionJPARepository, TransactionMapper transactionMapper) {
        this.transactionJPARepository = transactionJPARepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Transaction initiateTransaction(Transaction transaction) {
        transaction.initialiseTransaction();

        return transactionMapper.
                transactionEntityToTransaction(transactionJPARepository.save(transactionMapper.transactionToTransactionEntity(transaction)));
    }

    @Override
    @Transactional
    public Transaction processTransaction(TransactionId transactionId) {

        TransactionEntity transactionEntity = transactionJPARepository.findById(transactionId.getValue())
                .orElseThrow(() -> new TransactionException(String.format("Transaction with id %s not found", transactionId.getValue())));

        //TODO : stub implementation

        log.info("{} and {}",transactionEntity.getId(),transactionId.getValue());
        Transaction transaction = transactionMapper.transactionEntityToTransaction(transactionEntity, transactionEntity.getId().equals(transactionId.getValue()));
        transaction.processTransaction();
        transactionEntity.setTransactionStatus(transaction.getTransactionEventStatus());

        var savedEntity = transactionJPARepository.save(transactionEntity);
        CompletableFuture.runAsync(()-> processPayment(savedEntity));

        return transaction;

    }

    @Transactional
    public void processPayment(TransactionEntity savedEntity) {
        //TODO : stub implementation
//        savedEntity.setPaymentStatus(PaymentStatus.SUCCESSFUL);
//
//        transactionJPARepository.save(savedEntity);
    }

    @Override
    public Transaction confirmTransaction(TransactionId transactionId) {

        TransactionEntity transactionEntity = transactionJPARepository.findById(transactionId.getValue())
                .orElseThrow(() -> new TransactionException(String.format("Transaction with id %s not found", transactionId.getValue())));

        Transaction transaction = transactionMapper.transactionEntityToTransaction(transactionEntity);
        transaction.completeTransaction();
        transactionJPARepository.save(transactionMapper.transactionToTransactionEntity(transaction));

        return transaction;


    }

}

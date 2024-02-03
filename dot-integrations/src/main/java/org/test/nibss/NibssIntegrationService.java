package org.test.nibss;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.domain.Transaction;
import org.test.domain.valueobject.PaymentStatus;
import org.test.exception.TransactionException;
import org.test.mapper.TransactionObjectMapper;
import org.test.paymentProcessor;
import org.test.transaction.repository.AccountJPARepository;
import org.test.transaction.repository.TransactionJPARepository;

@Service
public class NibssIntegrationService implements paymentProcessor<Transaction> {


    private final TransactionJPARepository transactionJPARepository;
    private final TransactionObjectMapper transactionObjectMapper;
    private final AccountJPARepository accountJPARepository;

    public NibssIntegrationService(TransactionJPARepository transactionJPARepository, TransactionObjectMapper transactionObjectMapper, AccountJPARepository accountJPARepository) {
        this.transactionJPARepository = transactionJPARepository;

        this.transactionObjectMapper = transactionObjectMapper;
        this.accountJPARepository = accountJPARepository;
    }


    @Override
    @Transactional
    public void processPayment(Transaction transaction) {

        var entity = transactionJPARepository.findById(transaction.getId().getValue())
                .orElseThrow(()-> new TransactionException("For transaction entity found for this id"));


        var account = accountJPARepository.findAccountEntityByUserId(entity.getSubscriberId()).orElseThrow(()-> new TransactionException("Account not found for this "));

        entity.setPaymentStatus(PaymentStatus.SUCCESSFUL);





    }
}

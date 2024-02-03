package org.test;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.port.output.AccountRepository;
import org.test.port.output.TransactionRepository;

@Configuration
@SpringBootApplication(scanBasePackages = "org.test")
public class TestConfiguration {

    @Bean
    public TransactionRepository mockTransactionRepository(){

        return Mockito.mock(TransactionRepository.class);
    }

    @Bean
    public AccountRepository mockAccountRepository(){

        return Mockito.mock(AccountRepository.class);
    }

//    @Bean
//    @Primary
//    public TransactionApplicationService mockTransactionApplicationService(){
//        return Mockito.mock(TransactionApplicationServiceImp.class);
//    }
//
//    @Bean
//    public HandlerFactory<CreateTransactionResponse, CreateTransactionCommand>  getHandler(){
//        return Mockito.mock(InitiateTransactionHandler.class);
//    }
}

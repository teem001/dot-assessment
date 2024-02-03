package org.test.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.test.domain.valueobject.AccountStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {


    @Id
    private UUID id;
    private UUID userId;
    private String accountName;
    private String accountNumber;
    private BigDecimal accountBalance;
    private AccountStatus accountStatus;
    private String accountStatusMessage;
    private final LocalDateTime createdAt = LocalDateTime.now();

}

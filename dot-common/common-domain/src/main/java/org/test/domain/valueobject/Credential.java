package org.test.domain.valueobject;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Credential {

    public final String pin;
    private final LocalDateTime expiryTime;
    private final CredentialStatus credentialStatus;
    private final LocalDateTime createdTime = LocalDateTime.now();

    public Credential(String pin, LocalDateTime expiryTime, CredentialStatus credentialStatus) {
        this.pin = pin;
        this.expiryTime = expiryTime;
        this.credentialStatus = credentialStatus;
    }
}

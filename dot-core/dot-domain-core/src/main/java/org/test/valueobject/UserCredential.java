package org.test.valueobject;

import lombok.Getter;
import org.test.domain.valueobject.CredentialStatus;

@Getter
public class UserCredential {

    private  final CredentialStatus credentialStatus;
    private final String value;

    public UserCredential(CredentialStatus credentialStatus, String value) {
        this.credentialStatus = credentialStatus;
        this.value = value;

    }
}

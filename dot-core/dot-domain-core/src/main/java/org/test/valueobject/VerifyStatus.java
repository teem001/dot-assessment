package org.test.valueobject;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter

public class VerifyStatus {

    private final boolean isBVNVerified;
    private final boolean isIDVerified;

    private final Boolean isEmailVerified;
    @Setter
    private List<String> statusMessages;

    public VerifyStatus(boolean isBVNVerified, boolean isIDVerified, Boolean isEmailVerified) {
        this.isBVNVerified = isBVNVerified;
        this.isIDVerified = isIDVerified;
        this.isEmailVerified = isEmailVerified;
    }
}

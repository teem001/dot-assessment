package org.test.domain;

import lombok.Getter;
import org.test.domain.entity.BaseEntity;
import org.test.domain.valueobject.UserId;
import org.test.valueobject.UserCredential;
import org.test.valueobject.VerifyStatus;

import java.time.LocalDate;

@Getter
public class User extends BaseEntity<UserId> {

    private final String accountName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String BVN;
    private final VerifyStatus verifyStatus;
    private final LocalDate dateOfBirth;
    private final UserCredential credential;

    public User(String accountName, String firstName, String lastName, String email, String bvn, VerifyStatus verifyStatus, LocalDate dateOfBirth, UserCredential credential) {
        this.accountName = accountName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        BVN = bvn;
        this.verifyStatus = verifyStatus;
        this.dateOfBirth = dateOfBirth;
        this.credential = credential;
    }
}

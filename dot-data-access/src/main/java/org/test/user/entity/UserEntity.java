package org.test.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_info")
@Data
public class UserEntity {

    @Id
    private UUID userId;
    private String accountName;
    private String firstName;
    private String lastName;
    private String email;
    private String BVN;
    private Boolean isBVNVerified;
    private Boolean isIDVerified;

    private Boolean isEmailVerified;
    private Boolean isActive;
    private LocalDateTime createdAt = LocalDateTime.now();

    public UserEntity() {

    }
}

package org.test.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.account.entity.AccountEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountJPARepository extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findAccountEntityByUserId(UUID userid);
}

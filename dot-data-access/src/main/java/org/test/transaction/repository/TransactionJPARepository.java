package org.test.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.transaction.entity.TransactionEntity;

import java.util.UUID;

@Repository
public interface TransactionJPARepository extends JpaRepository<TransactionEntity, UUID> {


}

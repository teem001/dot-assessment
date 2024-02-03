package org.test.domain.valueobject;

import java.util.UUID;

public class TransactionId extends BaseId<UUID> {

    public TransactionId(UUID value){
        super(value);
    }
}

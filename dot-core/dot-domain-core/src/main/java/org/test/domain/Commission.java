package org.test.domain;

import lombok.Getter;
import lombok.Setter;
import org.test.domain.entity.BaseEntity;
import org.test.domain.valueobject.CommissionId;
import org.test.domain.valueobject.CommissionType;
import org.test.domain.valueobject.Money;

import java.math.BigDecimal;

@Getter
public class Commission extends BaseEntity<CommissionId> {

    private static final double  PERCENTAGE = 100;
    @Setter
    private CommissionType commissionType;
    @Setter
    private Double value;
    @Setter
    private Money capLimit;
    private final Money baseAmount;
    private Money commission;

    public Commission(Money baseAmount) {

        this.baseAmount = baseAmount;
    }



    public Money getCommissionAmount(){

        commission = commissionType == CommissionType.PERCENTAGE ? new Money((baseAmount.multiply(value).divide(PERCENTAGE).getAmount())) :
                commissionType == CommissionType.FEE ? new Money(new BigDecimal(value)) : Money.ZERO;

        return commission.isGreaterThan(capLimit) ? capLimit : commission;
    }

}

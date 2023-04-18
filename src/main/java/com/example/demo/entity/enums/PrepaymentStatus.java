package com.example.demo.entity.enums;

public enum PrepaymentStatus {
    FIX_PRICE(PrePaymentType.Currency),
    INTEREST_PREPAYMENT(PrePaymentType.Percent),
    FULL_PREPAYMENT(PrePaymentType.Currency),
    PER_DAY_PREPAYMENT(PrePaymentType.How_many_days);

    private PrePaymentType prePaymentType;

    PrepaymentStatus(PrePaymentType prePaymentType) {
        this.prePaymentType = prePaymentType;
    }

    public PrePaymentType getPrePaymentType() {
        return prePaymentType;
    }
}

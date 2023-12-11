package com.food.ordering.system.valueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private final BigDecimal amount;

    public static Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isGreaterThanZero() {
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money money){
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
    }

    public Money add(Money money){
        return new Money(setScaleForNumber(this.amount.add(money.getAmount())));
    }

    public Money subtract(Money money){
        return new Money(setScaleForNumber(this.amount.subtract(money.getAmount())));
    }

    public Money multiply(int multiplier){
        return new Money(setScaleForNumber(this.amount.multiply(new BigDecimal(multiplier))));
    }

    private BigDecimal setScaleForNumber(BigDecimal input){
        return input.setScale(2, RoundingMode.HALF_EVEN );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) {
            return false;
        }
        Money other = (Money) obj;
        return amount.equals(other.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}

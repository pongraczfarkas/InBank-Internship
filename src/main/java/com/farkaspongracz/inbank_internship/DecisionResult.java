package com.farkaspongracz.inbank_internship;

import java.math.BigDecimal;

public class DecisionResult {
    private String decision;
    private BigDecimal amount;
    private int period;

    public DecisionResult(String decision, BigDecimal amount, int period) {
        this.decision = decision;
        this.amount = amount;
        this.period = period;
    }

    public String getDecision() {
        return decision;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getPeriod() {
        return period;
    }
}

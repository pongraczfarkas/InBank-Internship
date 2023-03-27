package com.farkaspongracz.inbank_internship;

import java.math.BigDecimal;

public class DecisionRequest {
    private String personalCode;
    private BigDecimal loanAmount;
    private int loanPeriodMonths;

    public DecisionRequest(String personalCode, BigDecimal loanAmount, int loanPeriodMonths) {
        this.personalCode = personalCode;
        this.loanAmount = loanAmount;
        this.loanPeriodMonths = loanPeriodMonths;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public int getLoanPeriodMonths() {
        return loanPeriodMonths;
    }
}

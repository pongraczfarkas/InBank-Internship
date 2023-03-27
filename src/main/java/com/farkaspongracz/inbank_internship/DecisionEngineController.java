package com.farkaspongracz.inbank_internship;

import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

@RestController
@RequestMapping("/decision-engine")
public class DecisionEngineController {

    @PostMapping
    public DecisionResult calculateDecision(@RequestBody DecisionRequest request) {
        // Get credit modifier for given personal code from an external registry or mocked data
        BigDecimal creditModifier = getCreditModifier(request.getPersonalCode());
        BigDecimal creditScore = creditModifier.divide(request.getLoanAmount(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(request.getLoanPeriodMonths()));

        //with this creditScore setup everything will be negative. Change the creditScore to something fix

        BigDecimal maxLoanAmount = new BigDecimal("10000");
        BigDecimal minLoanAmount = new BigDecimal("2000");
        int maxLoanPeriod = 60;
        int minLoanPeriod = 12;

        BigDecimal approveAmount;
        int approvePeriod;

        if (creditScore.compareTo(BigDecimal.ONE) < 0) {
            approveAmount = BigDecimal.ZERO;
            approvePeriod = 0;
        } else {
            approveAmount = request.getLoanAmount().min(maxLoanAmount).max(minLoanAmount);
            approvePeriod = Math.min(maxLoanPeriod, Math.max(minLoanPeriod, request.getLoanPeriodMonths()));
        }

        Decision decision = (approveAmount.compareTo(BigDecimal.ZERO) == 0) ? Decision.NEGATIVE : Decision.POSITIVE;

        return new DecisionResult(decision.toString(), approveAmount, approvePeriod);
    }

    private BigDecimal getCreditModifier(String personalCode) {
        // Get credit modifier from an external registry or mocked data based on personal code
        return switch (personalCode) {
            case "49002010965" -> BigDecimal.ZERO;
            case "49002010976" -> BigDecimal.valueOf(100);
            case "49002010987" -> BigDecimal.valueOf(300);
            case "49002010998" -> BigDecimal.valueOf(1000);
            default ->
                    // Throw an exception if no credit modifier is found for the given personal code
                    throw new IllegalArgumentException("Invalid personal code");
        };
    }
}

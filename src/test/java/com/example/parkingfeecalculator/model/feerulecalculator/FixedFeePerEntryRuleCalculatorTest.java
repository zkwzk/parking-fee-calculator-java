package com.example.parkingfeecalculator.model.feerulecalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.example.parkingfeecalculator.model.FitResult;

public class FixedFeePerEntryRuleCalculatorTest {
    private final LocalTime ruleStartTime = LocalTime.of(10, 0);
    private final LocalTime ruleEndTime = LocalTime.of(16, 0);
    private final double ruleFee = 5;
    private final FixedFeePerEntryRuleCalculator feeCalculator = new FixedFeePerEntryRuleCalculator(ruleStartTime, ruleEndTime, ruleFee);
    
    @Test
    public void shouldReturnRuleFeeIfAcutalTimeRangeWithinRuleTimeRange() {
        LocalTime actualStartTime = LocalTime.of(11, 0);
        LocalTime actualEndTime = LocalTime.of(11, 30);
        FitResult result = new FitResult(true, actualStartTime, actualEndTime);
        assertEquals(ruleFee, feeCalculator.calculateCost(result));
    }

    @Test
    public void shouldReturn0IfActualTimeRangeOutsideRuleTimeRange() {
        LocalTime actualStartTime = LocalTime.of(9, 0);
        LocalTime actualEndTime = LocalTime.of(9, 30);
        FitResult result = new FitResult(false, actualStartTime, actualEndTime);
        assertEquals(0, feeCalculator.calculateCost(result));
    }
}

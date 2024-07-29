package com.example.parkingfeecalculator.model.feerulecalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.example.parkingfeecalculator.model.FitResult;

public class FixedFirstXMinutesRuleCalculatorTest {
    private LocalTime ruleStartTime = LocalTime.of(10, 0);
    private LocalTime ruleEndTime = LocalTime.of(16, 0);

    private int ruleXMinutes = 120;
    private int ruleYMinutes = 15;

    private double ruleXMinutesFee = 5;
    private double ruleYMinutesFee = 0.55;

    FixedFirstXMinutesRuleCalculator feeCalculator = new FixedFirstXMinutesRuleCalculator(ruleStartTime, ruleEndTime, ruleXMinutes, ruleXMinutesFee, ruleYMinutes, ruleYMinutesFee);

    @Test
    public void shouldReturnFistXMinutesFeeIfActualTimeRangeWithinXMinutes() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(11, 0);
        FitResult fitResult = new FitResult(true, actualStartTime, actualEndTime);
        double result = feeCalculator.calculateCost(fitResult);
        assertEquals(ruleXMinutesFee, result);
    }

    // task #1
    // TODO: implement the test cases for the calculateCost method
}

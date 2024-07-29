package com.example.parkingfeecalculator.model.feerulecalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void shouldReturn0IfActualTimeRangeBeforeRuleTimeRage() {
        LocalTime actualStartTime = LocalTime.of(9, 0);
        LocalTime actualEndTime = LocalTime.of(9, 30);
        FitResult fitResult = new FitResult(false, actualStartTime, actualEndTime);
        double result = feeCalculator.calculateCost(fitResult);
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnFirstXMinutesFeeIfAciutalTimeRangeExactlyMatchesXMinutes() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(12, 0);
        FitResult fitResult = feeCalculator.isFit(actualStartTime, actualEndTime);
        double result = feeCalculator.calculateCost(fitResult);
        assertTrue(fitResult.isFit());
        assertEquals(ruleXMinutesFee, result);
    }

    @Test
    public void shouldReturnFirstXMinutesFeeAndSubsequenceYMinutesFeeIfActualTimeRangeExceedsXMinutesByOneMinute() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(12, 1);
        FitResult fitResult = feeCalculator.isFit(actualStartTime, actualEndTime);
        double result = feeCalculator.calculateCost(fitResult);
        assertTrue(fitResult.isFit());
        assertEquals(ruleXMinutesFee + ruleYMinutesFee, result);
    }

    @Test
    public void shouldReturnFirstXMinutesFeeAndSubsequenceYMinutesFeeIfActualTimeRangeExceedsXMinutes() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(13, 7);
        FitResult fitResult = feeCalculator.isFit(actualStartTime, actualEndTime);
        double result = feeCalculator.calculateCost(fitResult);
        assertTrue(fitResult.isFit());
        assertEquals(ruleXMinutesFee + ruleYMinutesFee * 5, result);
    }

    @Test
    public void shouldReturnFirstXMinutesFeeAndSubsequenceYMinutesFeeIfActualTimeRangeExactlyMatchsRuleTimeRange() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(16, 0);
        FitResult fitResult = feeCalculator.isFit(actualStartTime, actualEndTime);
        double result = feeCalculator.calculateCost(fitResult);
        assertTrue(fitResult.isFit());
        assertEquals(ruleXMinutesFee + ruleYMinutesFee * 16, result);
    }
}

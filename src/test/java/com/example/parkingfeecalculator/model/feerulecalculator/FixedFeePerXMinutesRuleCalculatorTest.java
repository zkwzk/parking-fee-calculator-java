package com.example.parkingfeecalculator.model.feerulecalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.example.parkingfeecalculator.model.FitResult;

public class FixedFeePerXMinutesRuleCalculatorTest {
    private final LocalTime ruleStartTime = LocalTime.of(10, 0);
    private final LocalTime ruleEndTime = LocalTime.of(16, 0);
    private final double ruleXMinutesFee = 0.5;
    private final int ruleXMinutes = 15;

    private final FixedFeePerXMinutesRuleCalculator feeCalculator = new FixedFeePerXMinutesRuleCalculator(ruleStartTime, ruleEndTime, ruleXMinutes, ruleXMinutesFee);

    @Test
    public void shouldReturnXMinutesFeeIfActualTimeRangeWithinRuleTimeRange() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(11, 0);
        FitResult result = new FitResult(true, actualStartTime, actualEndTime);
        assertEquals(ruleXMinutesFee * 4, feeCalculator.calculateCost(result));
    }

    @Test
    public void shouldReturn0IfActualTimeRangeOutsideRuleTimeRange() {
        LocalTime actualStartTime = LocalTime.of(9, 0);
        LocalTime actualEndTime = LocalTime.of(9, 30);
        FitResult result = new FitResult(false, actualStartTime, actualEndTime);
        assertEquals(0, feeCalculator.calculateCost(result));
    }

    @Test
    public void shouldReturnXMinutesFeeIfActualStartTimeAndActualEndTimeAreSame() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(10, 0);
        FitResult result = feeCalculator.isFit(actualStartTime, actualEndTime);
        assertEquals(ruleXMinutesFee * 1, feeCalculator.calculateCost(result));
    }

    @Test
    public void shouldReturnRoundedXMinutesFeeIfActualTimeRangeExceedsRuleTimeRangeByOneMinute() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(10, 16);
        FitResult result = feeCalculator.isFit(actualStartTime, actualEndTime);
        assertEquals(ruleXMinutesFee * 2, feeCalculator.calculateCost(result));
    }

    @Test
    public void shouldReturnXMinutesFeeIfActualTimeRangeExactlyMatchesRuleTimeRange() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(16, 0);
        FitResult result = feeCalculator.isFit(actualStartTime, actualEndTime);
        assertEquals(ruleXMinutesFee * 24, feeCalculator.calculateCost(result));
    }

    @Test
    public void shouldReturnXMinutesFeeIfActualTimeRangeExceedsRuleTimeRange() {
        LocalTime actualStartTime = LocalTime.of(10, 0);
        LocalTime actualEndTime = LocalTime.of(17, 0);
        FitResult result = feeCalculator.isFit(actualStartTime, actualEndTime);
        assertEquals(ruleXMinutesFee * 24, feeCalculator.calculateCost(result));
    }
}

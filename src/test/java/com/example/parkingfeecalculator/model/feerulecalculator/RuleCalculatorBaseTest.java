package com.example.parkingfeecalculator.model.feerulecalculator;

import com.example.parkingfeecalculator.model.FitResult;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class RuleCalculatorBaseTest {

    private final RuleCalculatorBase feeCalculator = new RuleCalculatorBase(LocalTime.of(10, 0), LocalTime.of(12, 0));

    @Test
    public void ShouldReturnTrueIfActualTimeRangeWithinRuleTimeRange() {
        LocalTime actualStartTime = LocalTime.of(11, 0);
        LocalTime actualEndTime = LocalTime.of(11, 30);
        FitResult result = feeCalculator.isFit(actualStartTime, actualEndTime);
        assertTrue(result.isFit());
        assertEquals(result.getStartTime(), actualStartTime);
        assertEquals(result.getEndTime(), actualEndTime);
    }

    // task #2
    // TODO: implement the test cases for the isFit method

    @Test
    public void shouldThrowUnsupportedOperationExceptionWhenCalculateCostIsCalled() {
        LocalTime actualStartTime = LocalTime.of(11, 0);
        LocalTime actualEndTime = LocalTime.of(11, 30);
        FitResult result = feeCalculator.isFit(actualStartTime, actualEndTime);
        assertThrows(UnsupportedOperationException.class, () -> feeCalculator.calculateCost(result));
    }
}
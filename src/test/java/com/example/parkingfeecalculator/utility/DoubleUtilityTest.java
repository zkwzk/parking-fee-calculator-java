package com.example.parkingfeecalculator.utility;

import org.junit.jupiter.api.Test;

public class DoubleUtilityTest {
    @Test
    public void testRoundToTwoDecimalPlaces() {
        double value = 1.234567;
        double expected = 1.23;
        double result = DoubleUtility.roundToTwoDecimalPlaces(value);
        assert expected == result;
    }
}

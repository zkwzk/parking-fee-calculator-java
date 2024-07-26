package com.example.parkingfeecalculator.utility;

public class DoubleUtility {
    public static double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

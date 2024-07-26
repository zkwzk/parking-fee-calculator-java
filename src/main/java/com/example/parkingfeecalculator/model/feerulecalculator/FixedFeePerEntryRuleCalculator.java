package com.example.parkingfeecalculator.model.feerulecalculator;

import java.time.LocalTime;

import com.example.parkingfeecalculator.model.FitResult;
import com.example.parkingfeecalculator.utility.DoubleUtility;

public class FixedFeePerEntryRuleCalculator extends RuleCalculatorBase {
    private final double fee;

    public FixedFeePerEntryRuleCalculator(LocalTime startTime, LocalTime endTime, double fee) {
        super(startTime, endTime);
        this.fee = fee;
    }

    @Override
    public double calculateCost(FitResult fitResult) {
        return fitResult.isFit() ? DoubleUtility.roundToTwoDecimalPlaces(fee) : 0;
    }
}
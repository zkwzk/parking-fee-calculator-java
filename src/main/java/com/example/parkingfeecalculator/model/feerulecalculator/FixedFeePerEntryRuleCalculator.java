package com.example.parkingfeecalculator.model.feerulecalculator;

import java.time.LocalTime;

import com.example.parkingfeecalculator.model.FitResult;

public class FixedFeePerEntryRuleCalculator extends RuleCalculatorBase {
    private final double fee;

    public FixedFeePerEntryRuleCalculator(LocalTime startTime, LocalTime endTime, double fee) {
        super(startTime, endTime);
        this.fee = fee;
    }

    @Override
    public double calculateCost(FitResult fitResult) {
        return fitResult.isFit() ? fee : 0;
    }
}
package com.example.parkingfeecalculator.model.feerulecalculator;

import java.time.LocalTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.parkingfeecalculator.model.FitResult;
import com.example.parkingfeecalculator.utility.DoubleUtility;

public class FixedFeePerEntryRuleCalculator extends RuleCalculatorBase {
    final static Logger logger = LogManager.getLogger(FixedFeePerEntryRuleCalculator.class);
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
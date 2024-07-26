package com.example.parkingfeecalculator.model.feerulecalculator;

import java.time.LocalTime;

import com.example.parkingfeecalculator.model.FitResult;
import com.example.parkingfeecalculator.utility.DoubleUtility;

public class FixedFeePerXMinutesRuleCalculator extends RuleCalculatorBase {
    private final double xMinutesFee;
    private final int xMinutes;

    public FixedFeePerXMinutesRuleCalculator(LocalTime startTime, LocalTime endTime, int xMinutes, double xMinutesFee) {
        super(startTime, endTime);
        this.xMinutesFee = xMinutesFee;
        this.xMinutes = xMinutes;
    }

    @Override
    public double calculateCost(FitResult fitResult) {
        if(!fitResult.isFit()) {
            return 0;
        }
        
        if(fitResult.getStartTime().equals(fitResult.getEndTime())) {
            return xMinutesFee;
        }   

        int totalMinutes = (int) fitResult.getStartTime().until(fitResult.getEndTime(), java.time.temporal.ChronoUnit.MINUTES);
        int chargeableUnits = (int) Math.ceil((double) totalMinutes / xMinutes);
        return DoubleUtility.roundToTwoDecimalPlaces(this.xMinutesFee * chargeableUnits);
    }
}

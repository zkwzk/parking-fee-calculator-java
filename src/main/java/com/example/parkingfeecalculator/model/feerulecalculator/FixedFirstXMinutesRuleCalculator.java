package com.example.parkingfeecalculator.model.feerulecalculator;

import java.time.Duration;
import java.time.LocalTime;

import com.example.parkingfeecalculator.model.FitResult;
import com.example.parkingfeecalculator.utility.DoubleUtility;

import lombok.Getter;

@Getter
public class FixedFirstXMinutesRuleCalculator extends RuleCalculatorBase {
    private int xMinutes;
    private double firstXMinutesFee;
    private int yMinutes;
    private double subsequentYMinutesFee; 

    public FixedFirstXMinutesRuleCalculator(LocalTime startTime, LocalTime endTime, int xMinutes, double firstXMinutesFee, int yMinutes, double subsequentYMinutesFee) {
        super(startTime, endTime);
        this.xMinutes = xMinutes;
        this.firstXMinutesFee = firstXMinutesFee;
        this.yMinutes = yMinutes;
        this.subsequentYMinutesFee = subsequentYMinutesFee;
    }

    @Override
    public double calculateCost(FitResult fitResult) {
        if (!fitResult.isFit()) {
            return 0;
        }

        double totalCost = 0;
        int totalMinutes = (int)Duration.between(fitResult.getStartTime(), fitResult.getEndTime()).toMinutes();
        if (totalMinutes <= xMinutes) {
            return firstXMinutesFee;
        }
        
        totalCost = firstXMinutesFee + Math.ceil((totalMinutes - xMinutes) / (double)yMinutes) * subsequentYMinutesFee;

        return DoubleUtility.roundToTwoDecimalPlaces(totalCost);
    }
}

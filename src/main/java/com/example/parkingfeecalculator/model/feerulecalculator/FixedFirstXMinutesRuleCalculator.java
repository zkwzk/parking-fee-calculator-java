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
        // task #1
        // TODO: implement the calculateCost method

        return DoubleUtility.roundToTwoDecimalPlaces(0);
    }
}

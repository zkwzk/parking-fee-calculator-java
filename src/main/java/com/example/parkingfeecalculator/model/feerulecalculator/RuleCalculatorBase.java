package com.example.parkingfeecalculator.model.feerulecalculator;

import com.example.parkingfeecalculator.model.FitResult;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class RuleCalculatorBase {
    private final LocalTime startTime;
    private final LocalTime endTime;

    public RuleCalculatorBase(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public final FitResult isFit(LocalTime startTime, LocalTime endTime) {
        // task #2
        // TODO: implement the isFit method

        return new FitResult(
                false,
                startTime,
                endTime);
    }

    public double calculateCost(FitResult fitResult) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
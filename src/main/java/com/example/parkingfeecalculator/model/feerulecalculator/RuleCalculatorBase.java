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
        if ((startTime.isBefore(this.startTime) && endTime.isBefore(this.startTime)) ||
                (endTime.isAfter(this.endTime) && startTime.isAfter(this.endTime))) {
            return new FitResult(false, startTime, endTime);
        }

        return new FitResult(
                true,
                startTime.isAfter(this.startTime) ? startTime : this.startTime,
                endTime.isBefore(this.endTime) ? endTime : this.endTime
        );
    }

    public double calculateCost(FitResult fitResult) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
package com.example.parkingfeecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class FitResult {
    private boolean isFit;
    private LocalTime startTime;
    private LocalTime endTime;
}

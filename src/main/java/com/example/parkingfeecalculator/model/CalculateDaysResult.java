package com.example.parkingfeecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalculateDaysResult {
    private LocalTime dayStartTime;
    private LocalTime dayEndTime;
    private boolean isWeekend;
}
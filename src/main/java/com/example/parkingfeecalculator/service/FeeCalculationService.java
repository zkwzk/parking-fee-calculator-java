package com.example.parkingfeecalculator.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.parkingfeecalculator.model.CalculateDaysResult;
import com.example.parkingfeecalculator.model.CarPark;
import com.example.parkingfeecalculator.model.FitResult;
import com.example.parkingfeecalculator.model.VehicleType;
import com.example.parkingfeecalculator.model.feerulecalculator.RuleCalculatorBase;
import com.example.parkingfeecalculator.utility.DoubleUtility;

@Service
public class FeeCalculationService implements IFeeCalculationService {

    public boolean checkIsWeekend(LocalDateTime date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public boolean checkGracePeriod(LocalDateTime startTime, LocalDateTime endTime, int gracePeriodMinutes) {
        return Duration.between(startTime, endTime).toMinutes() <= gracePeriodMinutes;
    }

    public boolean checkIsSameDay(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.toLocalDate().equals(endTime.toLocalDate());
    }

    public List<CalculateDaysResult> calculateDays(LocalDateTime startTime, LocalDateTime endTime) {
        List<CalculateDaysResult> result = new ArrayList<>();

        if (checkIsSameDay(startTime, endTime)) {
            result.add(new CalculateDaysResult(startTime.toLocalTime(), endTime.toLocalTime(), checkIsWeekend(startTime)));
            return result;
        }

        LocalDate currentDay = startTime.toLocalDate();
        LocalTime currentStartTime = startTime.toLocalTime();
        LocalTime currentEndTime = LocalTime.of(23, 59);

        while (currentDay.isBefore(endTime.toLocalDate())) {
            result.add(new CalculateDaysResult(currentStartTime, currentEndTime, checkIsWeekend(currentDay.atStartOfDay())));
            currentDay = currentDay.plusDays(1);
            currentStartTime = LocalTime.of(0, 0);
        }

        result.add(new CalculateDaysResult(currentStartTime, endTime.toLocalTime(), checkIsWeekend(endTime)));
        return result;
    }

    @Override
    public double calculateParkingFee(LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType, CarPark carPark) {
        // #task 3
        // TODO: Implement the logic to calculate the parking fee
        return DoubleUtility.roundToTwoDecimalPlaces(0);
    }
}
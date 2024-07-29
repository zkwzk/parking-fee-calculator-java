package com.example.parkingfeecalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.parkingfeecalculator.model.CalculateDaysResult;
import com.example.parkingfeecalculator.model.CarPark;
import com.example.parkingfeecalculator.model.CarParkConfig;
import com.example.parkingfeecalculator.model.VehicleType;
import com.example.service.FeeCalculationService;

public class FeeCalculationServiceTest {
    private final FeeCalculationService service = new FeeCalculationService();

    @Test
    public void shouldReturnTrueIfItsSaturday() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 13, 10, 0, 0);
        boolean result = service.checkIsWeekend(startTime);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfItsFriday() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 10, 0, 0);
        boolean result = service.checkIsWeekend(startTime);
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfWithinGracePeriod() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 7, 12, 10, 10, 0);

        boolean result = service.checkGracePeriod(startTime, endTime, 10);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfExceedsGracePeriod() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 7, 12, 10, 11, 0);

        boolean result = service.checkGracePeriod(startTime, endTime, 10);
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfAcrossDayAndWithinGracePeriod() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 23, 50, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 7, 13, 0, 0, 0);

        boolean result = service.checkGracePeriod(startTime, endTime, 10);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfAcrossDayAndExceedsGracePeriod() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 23, 50, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 7, 13, 0, 1, 0);

        boolean result = service.checkGracePeriod(startTime, endTime, 10);
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfItsSameDay() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 7, 12, 10, 10, 0);
        boolean result = service.checkIsSameDay(startTime, endTime);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfNotSameDay() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 23, 50, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 7, 13, 0, 0, 0);
        boolean result = service.checkIsSameDay(startTime, endTime);
        assertFalse(result);
    }

    @Test
    public void shouldReturnTwoItemsInTheResultForCalculateDaysIfItsAcrossDays() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 7, 13, 10, 0, 0);
        List<CalculateDaysResult> result = service.calculateDays(startTime, endTime);
        assertEquals(2, result.size());
        assertEquals(LocalTime.of(10, 0), result.get(0).getDayStartTime());
        assertEquals(LocalTime.of(23, 59), result.get(0).getDayEndTime());
        assertFalse(result.get(0).isWeekend());
        assertEquals(LocalTime.of(0, 0), result.get(1).getDayStartTime());
        assertEquals(LocalTime.of(10, 0), result.get(1).getDayEndTime());
        assertTrue(result.get(1).isWeekend());
    }

    @Test
    public void shouldReturnOneItemInTheResultForCalculateDaysIfItsSameDay() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 7, 12, 16, 0, 0);
        List<CalculateDaysResult> result = service.calculateDays(startTime, endTime);
        assertEquals(1, result.size());
        assertEquals(LocalTime.of(10, 0), result.get(0).getDayStartTime());
        assertEquals(LocalTime.of(16, 0), result.get(0).getDayEndTime());
        assertFalse(result.get(0).isWeekend());
    }

    @Test
    public void shouldReturnZeroIfItsWithinGracePeriod() {
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 7, 12, 10, 10, 0);
        CarPark carPark = CarParkConfig.plazaSingapuraCarPark;
        double result = service.calculateParkingFee(startTime, endTime, VehicleType.CAR, carPark);
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnCorrectFeeForCalculateParkingFeeIfItsSameDay() {
        LocalDateTime startTime = LocalDateTime.of(2021, 1, 1, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 1, 1, 11, 0, 0);
        CarPark carPark = CarParkConfig.plazaSingapuraCarPark;
        double result = service.calculateParkingFee(startTime, endTime, VehicleType.CAR, carPark);
        assertEquals(1.95, result);
    }

    @Test
    public void shouldReturnCorrectFeeForCalculateParkingFeeIfIts2Hours() {
        LocalDateTime startTime = LocalDateTime.of(2021, 1, 1, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 1, 1, 12, 0, 0);
        CarPark carPark = CarParkConfig.plazaSingapuraCarPark;
        double result = service.calculateParkingFee(startTime, endTime, VehicleType.CAR, carPark);
        assertEquals(4.15, result);
    }

    @Test
    public void shouldReturnCorrectFeeForCalculateParkingFeeIfItsOneWeekdayAndOneWeekend() {
        LocalDateTime startTime = LocalDateTime.of(2021, 1, 1, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 1, 2, 11, 0, 0);
        CarPark carPark = CarParkConfig.plazaSingapuraCarPark;
        double result = service.calculateParkingFee(startTime, endTime, VehicleType.CAR, carPark);
        assertEquals(43.65, result);
    }

    @Test
    public void shouldReturnCorrectFeeForCalculateParkingFeeIfItsOneWeekdayAndTwoWeekend() {
        LocalDateTime startTime = LocalDateTime.of(2021, 1, 1, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 1, 3, 12, 0, 0);
        CarPark carPark = CarParkConfig.plazaSingapuraCarPark;
        double result = service.calculateParkingFee(startTime, endTime, VehicleType.CAR, carPark);
        assertEquals(87.55, result);
    } 

    @Test
    public void shouldReturnCorrectFeeForCalculateParkingFeeForMotorcycle() {
        LocalDateTime startTime = LocalDateTime.of(2021, 1, 1, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 1, 1, 12, 0, 0);
        CarPark carPark = CarParkConfig.plazaSingapuraCarPark;
        double result = service.calculateParkingFee(startTime, endTime, VehicleType.MOTORCYCLE, carPark);
        assertEquals(1.3, result); 
    }

    @Test
    public void plazaSingapuraShouldBeTheLowest() {
        LocalDateTime startTime = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 1, 1, 23, 59, 0);
        double result = service.calculateParkingFee(startTime, endTime, VehicleType.CAR, CarParkConfig.plazaSingapuraCarPark);
        assertEquals(42.6, result);

        result = service.calculateParkingFee(startTime, endTime, VehicleType.CAR, CarParkConfig.orchardCentralCarPark);
        assertEquals(53.06, result);

        result = service.calculateParkingFee(startTime, endTime, VehicleType.CAR, CarParkConfig.tscCarPark);
        assertEquals(58.46, result);
    }
}

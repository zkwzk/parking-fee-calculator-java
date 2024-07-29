package com.example.parkingfeecalculator.service;

import java.time.LocalDateTime;

import com.example.parkingfeecalculator.model.CarPark;
import com.example.parkingfeecalculator.model.VehicleType;

public interface IFeeCalculationService {
    double calculateParkingFee(LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType, CarPark carPark); 
}

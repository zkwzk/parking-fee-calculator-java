package com.example.parkingfeecalculator.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkingfeecalculator.model.CarPark;
import com.example.parkingfeecalculator.model.CarParkConfig;
import com.example.parkingfeecalculator.model.CarParkFee;
import com.example.parkingfeecalculator.model.VehicleType;
import com.example.parkingfeecalculator.service.FeeCalculationService;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/feecalculator")
public class FeeCalculationController {
    final static Logger logger = LogManager.getLogger(FeeCalculationController.class);
    @Autowired
    private FeeCalculationService service;
    
    @GetMapping("/getLowestCarpark")
    public ResponseEntity<?> getLowestCarpark(
        @Parameter(description = "Start time in ISO format", example = "2024-03-20T10:00:00") @RequestParam(value="startTime",required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, 
        @Parameter(description = "End time in ISO format", example = "2024-03-22T12:00:00") @RequestParam(value="endTime", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime, 
        @Parameter(description = "Vehicle Type, Car or Motorcycle", example = "Car") @RequestParam(value="vehicleType", required = true) String vehicleType) {
        logger.debug("getLowestCarpark called");

        List<CarParkFee> carParkFees = new ArrayList<CarParkFee>();
        if(vehicleType.toLowerCase().equals("car")) {
            for (CarPark carPark : CarParkConfig.CarParks) {
                carParkFees.add(new CarParkFee(carPark.getName(), service.calculateParkingFee(startTime, endTime, VehicleType.CAR, carPark)));
            }
        } else if (vehicleType.toLowerCase().equals("motorcycle")) {
            List<CarPark> motorcycleEnabledCarpark = CarParkConfig.CarParks.stream().filter(c -> c.getMotorcycleFeeRulesCalculators().size() > 0).toList();
            for (CarPark carPark : motorcycleEnabledCarpark) {
                carParkFees.add(new CarParkFee(carPark.getName(), service.calculateParkingFee(startTime, endTime, VehicleType.MOTORCYCLE, carPark)));
            }
        } else {
            // return 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid vehicle type");
        }

        return ResponseEntity.ok().body(carParkFees.stream().min((a, b) -> Double.compare(a.getFee(), b.getFee())).get());
    }
}

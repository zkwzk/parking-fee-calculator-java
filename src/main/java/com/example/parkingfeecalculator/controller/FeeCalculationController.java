package com.example.parkingfeecalculator.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping("/api/feecalculator")
public class FeeCalculationController {
    @Autowired
    private FeeCalculationService service;
    
    @GetMapping("/getLowestCarpark")
    public ResponseEntity<?> getLowestCarpark(
        @RequestParam(value="startTime",required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, 
        @RequestParam(value="endTime", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime, 
        @RequestParam(value="vehicleType", required = true) String vehicleType) {
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

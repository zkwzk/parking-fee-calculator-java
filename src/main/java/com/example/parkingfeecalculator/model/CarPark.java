package com.example.parkingfeecalculator.model;

import java.util.ArrayList;
import java.util.List;

import com.example.parkingfeecalculator.model.feerulecalculator.RuleCalculatorBase;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarPark {
    private final String name; 
    private final int gracePeriodInMinutes;
    private final List<RuleCalculatorBase> carWeekdayFeeRulesCalculators;
    private final List<RuleCalculatorBase> carWeekendFeeRulesCalculators;
    private final List<RuleCalculatorBase> motorcycleFeeRulesCalculators;
}

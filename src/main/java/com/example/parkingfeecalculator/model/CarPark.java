package com.example.parkingfeecalculator.model;

import java.util.ArrayList;
import java.util.List;

import com.example.parkingfeecalculator.model.feerulecalculator.RuleCalculatorBase;

import lombok.Getter;

@Getter
public class CarPark {
    private final String name; 
    private final int gracePeriodInMinutes;
    private final List<RuleCalculatorBase> carWeekdayFeeRulesCalculators;
    private final List<RuleCalculatorBase> carWeekendFeeRulesCalculators;
    private final List<RuleCalculatorBase> motorcycleFeeRulesCalculators;
    
    public CarPark(String name, int gracePeriodInMinutes) {
        this.name = name;
        this.gracePeriodInMinutes = gracePeriodInMinutes;
        this.carWeekdayFeeRulesCalculators = new ArrayList<RuleCalculatorBase>();
        this.carWeekendFeeRulesCalculators = new ArrayList<RuleCalculatorBase>();
        this.motorcycleFeeRulesCalculators = new ArrayList<RuleCalculatorBase>();
    }
}

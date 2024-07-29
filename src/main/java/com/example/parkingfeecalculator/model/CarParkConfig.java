package com.example.parkingfeecalculator.model;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import com.example.parkingfeecalculator.model.feerulecalculator.FixedFeePerEntryRuleCalculator;
import com.example.parkingfeecalculator.model.feerulecalculator.FixedFeePerXMinutesRuleCalculator;
import com.example.parkingfeecalculator.model.feerulecalculator.FixedFirstXMinutesRuleCalculator;

public final class CarParkConfig {
    private static final FixedFirstXMinutesRuleCalculator plazaSingapuraCarWeekday0to1759 =
            new FixedFirstXMinutesRuleCalculator(LocalTime.of(0, 0), LocalTime.of(17, 59), 60, 1.95, 15, 0.55);
    private static final FixedFeePerEntryRuleCalculator plazaSingapuraCarWeekday18to2359 =
            new FixedFeePerEntryRuleCalculator(LocalTime.of(18, 0), LocalTime.of(23, 59), 3.25);

    private static final FixedFeePerXMinutesRuleCalculator plazaSingapuraCarWeekendPH0to0259 =
            new FixedFeePerXMinutesRuleCalculator(LocalTime.of(0, 0), LocalTime.of(2, 59), 15, 0.55);
    private static final FixedFirstXMinutesRuleCalculator plazaSingapuraCarWeekendPH03to1759 =
            new FixedFirstXMinutesRuleCalculator(LocalTime.of(3, 0), LocalTime.of(17, 59), 120, 3.25, 15, 0.55);
    private static final FixedFeePerEntryRuleCalculator plazaSingapuraCarWeekend18to2359 =
            new FixedFeePerEntryRuleCalculator(LocalTime.of(18, 0), LocalTime.of(23, 59), 3.25);

    private static final FixedFeePerEntryRuleCalculator plazaSingapuraMotorcyclePerEntry =
            new FixedFeePerEntryRuleCalculator(LocalTime.of(0, 0), LocalTime.of(23, 59), 1.3);

    public static final CarPark plazaSingapuraCarPark = new CarPark(
            "Plaza Singapura Car Park",
            15,
            Arrays.asList(
                    plazaSingapuraCarWeekday0to1759,
                    plazaSingapuraCarWeekday18to2359
            ),
            Arrays.asList(
                    plazaSingapuraCarWeekendPH0to0259,
                    plazaSingapuraCarWeekendPH03to1759,
                    plazaSingapuraCarWeekend18to2359
            ),
            Arrays.asList(
                    plazaSingapuraMotorcyclePerEntry
            )
    );

    private static final FixedFirstXMinutesRuleCalculator orchardCentralWeekday0to1759 =
    new FixedFirstXMinutesRuleCalculator(LocalTime.of(0, 0), LocalTime.of(17, 59), 60, 2.73, 15, 0.68);
    private static final FixedFeePerEntryRuleCalculator orchardCentralWeekday18to2359 =
        new FixedFeePerEntryRuleCalculator(LocalTime.of(18, 0), LocalTime.of(23, 59), 4.09);

    private static final FixedFirstXMinutesRuleCalculator orchardCentralWeekendPH0to1759 =
        new FixedFirstXMinutesRuleCalculator(LocalTime.of(0, 0), LocalTime.of(17, 59), 60, 2.94, 15, 0.74);
    private static final FixedFeePerEntryRuleCalculator orchardCentralWeekendPH18to2359 =
        new FixedFeePerEntryRuleCalculator(LocalTime.of(18, 0), LocalTime.of(23, 59), 4.41);

    public static final CarPark orchardCentralCarPark = new CarPark(
        "Orchard Central Car Park",
        10,
        Arrays.asList(
                orchardCentralWeekday0to1759,
                orchardCentralWeekday18to2359
        ),
        Arrays.asList(
                orchardCentralWeekendPH0to1759,
                orchardCentralWeekendPH18to2359
        ),
        Arrays.asList()
    );

    private static final FixedFeePerXMinutesRuleCalculator tscWeekday0to1159 =
    new FixedFeePerXMinutesRuleCalculator(LocalTime.of(0, 0), LocalTime.of(11, 59), 30, 1.31);
    private static final FixedFeePerXMinutesRuleCalculator tscWeekday12to1359 =
        new FixedFeePerXMinutesRuleCalculator(LocalTime.of(12, 0), LocalTime.of(13, 59), 30, 1.85);
    private static final FixedFeePerXMinutesRuleCalculator tscWeekday14to1659 =
        new FixedFeePerXMinutesRuleCalculator(LocalTime.of(14, 0), LocalTime.of(16, 59), 30, 1.31);
    private static final FixedFeePerXMinutesRuleCalculator tscWeekday17to1859 =
        new FixedFeePerXMinutesRuleCalculator(LocalTime.of(17, 0), LocalTime.of(18, 59), 30, 1.85);
    private static final FixedFeePerEntryRuleCalculator tscWeekday19to2359 =
        new FixedFeePerEntryRuleCalculator(LocalTime.of(19, 0), LocalTime.of(23, 59), 4.36);

    private static final FixedFirstXMinutesRuleCalculator tscWeekendPH0to1159 =
        new FixedFirstXMinutesRuleCalculator(LocalTime.of(0, 0), LocalTime.of(11, 59), 60, 2.62, 30, 1.64);
    private static final FixedFirstXMinutesRuleCalculator tscWeekendPH12to1359 =
        new FixedFirstXMinutesRuleCalculator(LocalTime.of(12, 0), LocalTime.of(13, 59), 60, 3.71, 30, 2.18);
    private static final FixedFirstXMinutesRuleCalculator tscWeekendPH14to1659 =
        new FixedFirstXMinutesRuleCalculator(LocalTime.of(14, 0), LocalTime.of(16, 59), 60, 2.62, 30, 1.64);
    private static final FixedFirstXMinutesRuleCalculator tscWeekendPH17to1859 =
        new FixedFirstXMinutesRuleCalculator(LocalTime.of(17, 0), LocalTime.of(18, 59), 60, 3.71, 30, 2.18);
    private static final FixedFeePerEntryRuleCalculator tscWeekendPH19to2359 =
        new FixedFeePerEntryRuleCalculator(LocalTime.of(19, 0), LocalTime.of(23, 59), 4.36);

    public static final CarPark tscCarPark = new CarPark(
        "Takashimaya Shopping Centre",
        10,
        Arrays.asList(
                tscWeekday0to1159,
                tscWeekday12to1359,
                tscWeekday14to1659,
                tscWeekday17to1859,
                tscWeekday19to2359
        ),
        Arrays.asList(
                tscWeekendPH0to1159,
                tscWeekendPH12to1359,
                tscWeekendPH14to1659,
                tscWeekendPH17to1859,
                tscWeekendPH19to2359
        ),
        Arrays.asList()
    );

    public static List<CarPark> CarParks = Arrays.asList(
        plazaSingapuraCarPark,
        orchardCentralCarPark,
        tscCarPark
    );
}

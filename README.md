# Parking Fee Calculator

You are tasked to build a Parking Fee Calculator, that will help to calculate parking fees and thus help users decide where to park.
These are the carparks that will be considered in this application:

![alt text](carpark_data.png)

For each of the car park, it has different parking rules, the rules for weekday and weekend are also different, in general there are three kinds of parking rule, 

**Fixed per entry**: Per Entry charge - $4.36

**Fixed first X minutes, then subsequent Y mins charge**: 
First hour(X=60) at $1.95
Every subsequent 15 minutes(Y=15) at $0.55

**Fixed per X minutes**: $0.55 every 15 minutes(X=15)

## Project Structure

```
PARKING-FEE-CALCULATOR-JAVA
├── src/main/java/com/example/parkingfeecalculator
│   ├── controller
│   │   └── FeeCalculationController.java
│   ├── model
│   │   └── CarPark.java
│   │   └── CarParkConfig.java
│   │   └── CalculateDaysResult.java
│   │   └── CarParkFee.java
│   │   └── FitResult.java
│   │   └── VehicleType.java
│   │   └── feerulecalculator
│   │       └── FixedFeePerEntryRuleCalculator.java
│   │       └── FixedFeePerXMinutesRuleCalculator.java
│   │       └── FixedFirstXMinutesRuleCalculator.java
│   │       └── RuleCalculatorBase.java
│   ├── Services
│   │   └── FeeCalculationService.java
│   │   └── IFeeCalculationService.java
│   ├── Utilities
│   │   └── DoubleUtility.java
│   ├── App.java
├── test/java/com/example/parkingfeecalculator
│   ├── controller
│   │   └── FeeCalculationControllerTest.java
│   ├── model
│   │   └── feerulecalculator
│   │       └── FixedFeePerEntryRuleCalculatorTest.java
│   │       └── FixedFeePerXMinutesRuleCalculatorTest.java
│   │       └── FixedFirstXMinutesRuleCalculator.java
│   │       └── RuleCalculatorBaseTest.java
│   ├── ... test files
└── README.md
```

## File Descriptions

- `App.java`: this is the application file
- `controller/FeeCalculationController.java`: This file exports a class `FeeCalculationController` which has a method `getLowestCarpark` that handles the `/api/feeCalculator/getLowestCarpark` route of the application and returns the lowest carpark fee.
- `model/CarPark.java`: this is the model for the carpark fee rule, including the fee rule calculator for cars in weekday and weekend, and the fee rule calculator for motorcycle
- `model/CarParkConfig.java`: this is the configuration for the 3 carparks
- `model/CalculateDaysResult.java`: this is the model for `calculateDays` method in `feeCalculationService` class, it include the startTime and endTime for that day, for example, if a car entry the carpark at `1st Jan 1pm`, exit the carpark at `2nd Jan 3am`, in total there will be two days, then the startTime for the first day will be `1:00pm`, endTime will be `11:59pm`, the startTime for the second day will be `0:0am`, endTime will be `3:00am`
- `model/CarParkFee.java`: includes the carpark name and fee
- `model/FitResult.java`: this is the model for the result of `isFit` method in `RuleCalculatorBase` class 
- `model/feerulecalculator/RuleCalculatorBase.java`: this file  implements the `IsFit` method, which returns the time period that falls within the ruleset, or a false result if the inputted time period does not fall within the ruleset at all.
- `model/feerulecalculator/FixedFeePerEntryRuleCalculator.java`: this file defines the ruleset for which there is a fixed fee per entry for the time period defined in the ruleset.
- `model/feerulecalculator/FixedFeePerXMinutesRuleCalculator.java`: this file defines the ruleset for which there is a fixed fee per X minutes for the time period defined in the ruleset. Each block will be rounded up (i.e. if X=15, if the parking duration is 50minutes, it will be counted as 4 blocks of X)
- `model/feerulecalculator/FixedFirstXMinutesRuleCalculator.java`: this file defines the ruleset for which there is a fixed fee for the first X minutes, and there after the remaining time is charged for another fixed fee per Y minutes. Similarly, each block will be rounded up. (i.e. if X=60 and Y=15, if the parking duration is 110minutes the parking charge will be xFee + 4 \* yFee).
- `service/IFeeCalculationService.java`: this is the interface which include a `calculateParkingFee` method, to calculate the parking fee based on the `startTime` `endTime` `vehicleType` and `carPark`
- `service/FeeCalculationService.java`: this implements the IFeeRuleCalculator interface
- `utility/DoublelUtility.java`: This file provide an utility method `roundToTwoDecimalPlaces()` for the `double` type to fix it to 2 decimal places

## Class Diagram
![class diagram](./class_diagram.png)

## Time Sequence Diagram
![time sequence diagram](./calculate_parking_fee.png)

## Install(Visual Studio Code Configuration)

1. install Extension pack for Java(https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

## Build

```
./gradlew build
```

## Test
```
./gradlew test
```

<!-- ## Runc
```
./gradlew start
``` -->

## Run
```
./gradlew bootRun
```

it will run localhost, `http://localhost:8080`, 
can access the swagger by `http://localhost:8080/swagger-ui/index.html`
can access the api by `http://localhost:8080/api/feeCalculator/getLowestCarpark`

## How to use it
You need to finish 3 tasks. if all 3 tasks finished, when you run the test in `src/test/java/com/example/parkingfeecalculator/service/FeeCalculationServiceTest.java`, the test will pass!

### Task 1
---
can checkout the branch `step1` to start

```bash
git checkout step1
```

#### Requirement
You need to implement the `calculateCost` method in `FixedFirstXMinutesRuleCalculator.java`, it based on the rule `first X mintues charge a fixed amount, for the subsequence, each Y mintues will charge a fee`, for example:

10:00 - 16:00: 

First 2 hour at $5

Every subsequent 15 minutes at $0.55

X=120, Y=15

**test case:**

entryTime: 10:00, exitTime: 13:07

10:00-12:00: $5

12:01-13:07 (1*4+1)*0.55=$2.75

in total: 5+2.75=$7.75

#### Definiation of Done
all test cases in `FixedFirstXMinutesRuleCalculatorTest.java` are passed

### Task 2
---
**you can continue your implementation without the checkout command if you finished `step1`, or start directly by checkout the branch `step2`**

```bash
git checkout step2
````

#### Requirement

You need to implement the `isFit` method inside the `RuleCalculatorBase` class, it's to comparing the input startTime and endTime, whether it fit the current rule, if yes, need to return the actual start time and actual end time,

for example:
rule configed: 10:00-13:00

case 1:
input param: startTime: 9:00, endTime: 9:59
output: `{ isFit: false }`

case 2:
input param: startTime: 9:00, endTime: 11:01
output: `{ isFit: true, startTime: 10:00, endTime: 11:01 }`

#### Definition of Done
All the test cases in `RuleCalculatorBaseTest.java` are passed

### Task 3
---
**you can continue your implementation without the checkout command if you finished `step2`, or start directly by checkout the branch `step3`**

```bash
git checkout step3
```

#### Requirement
You need to finish the final logic, the `calculateParkingFee` method in `FeeCalculationService` to calculate the parking fee, can refer the time sequence diagram above
1. need to check whether it's within grace peroid, if yes return 0, the `checkGracePeriod` method will return true if it's within grace period
2. need to check whether it's weekend, if yes need to use the weekend day rule instead of the week day, the `checkIsWeekend` method will return true if it's weekend
3. split the start time and end time into days with `calculateDays` method
4. iterate the rules with each days and sum the result

**Test cases:**

entryTime = "2021-01-01T10:00";

exitTime = "2021-01-02T11:00";

parking in plazaSingapura

**day 1:**

10:00-11:00: 1.95

11:01-17:59: 7\*4*0.55 = 15.40

18:00-23:59: 3.25

**day 2:**

00:00-02:59: 3\*4*0.55 = 6.60

3:00-5:00: 3.25

5:01-11:00: 6\*4*0.55 = 13.2

total: 1.95 + 15.40 + 3.25 + 6.60 + 3.25 + 13.2 = 43.65

#### Definition of Done
All the test cases in `FeeCalculationServiceTest.java`(unit test) are all passed
and the test cases in `FeeCalculatorControllerTest.java`(api test) are passed


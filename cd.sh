#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Function to handle script termination
cleanup() {
  echo "Shutting down the application..."
  kill $BOOTRUN_PID
  wait $BOOTRUN_PID
  echo "Application stopped."
}

# Trap script termination signals (e.g., Ctrl+C)
trap cleanup SIGINT SIGTERM

# check the credentials with talisman
./talisman --pattern="./**/*.java"

# analyze the code dependencies
./gradlew dependencyCheckAnalyze --info

# gradlew build
echo "Building the application..."
./gradlew build
echo "Application built successfully."
echo "Running the tests..."
./gradlew test
echo "Tests passed successfully."
echo "Running the jacoco test coverage report..."
./gradlew jacocoTestReport

# Extract the missed and covered instruction counts from the JaCoCo XML report
MISSED=$(sed -n 's/.*<counter type="INSTRUCTION" missed="\([0-9]*\)".*/\1/p' build/reports/jacoco/test/jacocoTestReport.xml | awk '{s+=$1} END {print s}')
COVERED=$(sed -n 's/.*<counter type="INSTRUCTION" missed="[0-9]*" covered="\([0-9]*\)".*/\1/p' build/reports/jacoco/test/jacocoTestReport.xml | awk '{s+=$1} END {print s}')

# Calculate the total instructions and the coverage percentage
TOTAL=$((MISSED + COVERED))
COVERAGE=$(echo "scale=2; 100 * $COVERED / $TOTAL" | bc)

# Check if the coverage is above 90%
if (( $(echo "$COVERAGE < 90" | bc -l) )); then
  echo "Test coverage is below 90%: $COVERAGE%"
  exit 1
else
  echo "Test coverage is above 90%: $COVERAGE%"
fi

echo "Test coverage report generated successfully."
# Run the application

# run the spotbugs SAST
./gradlew spotbugsMain
echo "Running the application..."
./gradlew bootRun &
BOOTRUN_PID=$!

# Wait for the application to start
echo "Waiting for the application to start... Press Ctrl+C to stop"
sleep 5

# Open the browser to localhost:8080
echo "Opening the browser..."
open http://localhost:8080/swagger-ui/index.html

echo "Running the API tests..."
./gradlew apiTest &

# Wait for the bootRun process to finish
wait $BOOTRUN_PID
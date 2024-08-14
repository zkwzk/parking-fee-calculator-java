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

# gradlew build
echo "Building the application..."
## TODO-1 write command to build the application

echo "Application built successfully."
echo "Running the tests..."
## TODO-2 write command to run the unit tests

echo "Tests passed successfully."
echo "Running the jacoco test coverage report..."
## TODO-3 write command to generate the jacoco test coverage report

# Extract the missed and covered instruction counts from the jacoco XML report

## TODO-4 write command to extract the missed and covered instruction counts from the jacoco XML report


echo "Test coverage report generated successfully."
# TODO-5 Check if the coverage is above 90%, if not fail the build, print the coverage and exit the script


# Run the application

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

# TODO-6 Run api tests in parallel
# Wait for the bootRun process to finish
wait $BOOTRUN_PID
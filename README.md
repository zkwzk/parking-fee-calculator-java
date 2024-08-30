# Local CI CD Practise branch

You are going to implement a bash script `cd.sh` to build a local pipeline for building and deploying to local environments, which include the following steps:
1. Build(./gradlew build)
2. Unit test./gradlew test)
3. generate test coverage, threshold 90%(./gradlew jacocoTestReport), the report will be in build/reports/jacoco/test/jacocoTestReport.xml), need to calculate the line coverage based on the covered / (missed + covered), can use github copilot
4. Setup local env(./gradlew bootRun)
5. Run api test on local env(./gradlew apiTest)

For each of the step, if failed, then exit directly

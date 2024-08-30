# Secure CD Branch

This branch is to practise the security related activities

## Prerequisites
download the talisman

```
brew install wget

chmod +x downloadtalisman.sh

./downloadtalisman.sh
```

## Steps

1. check credentials with talisman **before build**
    1. Add the cmd to run the talisman for java files before build ( with argument `--pattern="./**/*.java"`)
    2. Remove the detected credentials from the code
2. check the dependencies **before build**
    1. Add the cmd to run the dependency analyze before build(first time run will take a quite long time, can comment out)
    2. Check the report
3. SAST after the unit test with spotbugs
    1. use `./gradlew task` to check all possible gradlew task
    2. Add the command to run the spotbugsmain
    3. See the report at: ./build/reports/spotbugs/main.html

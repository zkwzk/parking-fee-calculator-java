# Log Practise Branch

This branch include an application bug, which you need to try to add logs to address and fix the issue

## Simulate Background

Now we have a production issue after latest deployment, it cannot calculate the parking fee correctly, and the previous developer resigned, you two, the new joiner developers, need to fix this issue as soon as possible, the boss already agreed that the system can have a downtime, you can deploy as much time as possible, but you need to fix this issue within 30 mins
Unfortunately, the previous developer(the author of the repo :p) had a poor practise, he didn’t aware the importance of the logs, so there are no much useful log, you two, must add some logs to debug the root cause with log best practises

## Steps

1. Run the `./gradlew bootRun` in one console, then run the `./gradlew apiTest` in another console, you will find it failed, then stop the `./gradlew bootRun`(you can also use swagger http://localhost:8080/swagger-ui.html)
2. Add the tracking logs, to print out the trace and parameters values, and then check the log file trace.log in the root folder, and see which step it failed, can refer the comments for the expected value, and then compare with the output, to locate the issue
3. Fix it and run the ./gradlew bootRun and the ./gradlew apiTest again, to make it pass

## Addtional Notes

when first time run the `./gradlew bootRun`, it will generate a `counter.txt` file in the root folder, then each of time running the `bootRun`, it will increase by 1, you can `cat counter.txt` to see how many times you run the `bootRun`, means how many deployment you need
# Feature Toggle Demo Branch

This is the branch to demo the feature toggle, it will change the behavior of the `/api/featuretoggledemo/hello` endpoint at runtime.

## How to use

1. at the begining, keep the feature toggle off, can modify the toggle here https://www.npoint.io/docs/0752974a4a6668c526af, for now this url is hardcoded in the `FeatureToggleDemoController`, if the feature toggle url is gone and you don't have access to modify the code, can fork this repo and do your own
2. run the `./gradlew bootRun` cmd and go to the swagger `http://localhost:8080/swagger-ui/index.html#/feature-toggle-demo-controller/hello`, click the `Try it out` and `Execute`, it will return 403
3. now go to the https://www.npoint.io/docs/0752974a4a6668c526af and change the toggle to `true`, and trigger the endpiont again, it will return 200 with `Hello World!`



# jenkins-test-ops
Stop testing Jenkins implementation details. JenkinsTestOps provides dependency injection and test doubles for pipeline operations. Test business logic, not string-matched shell commands. Plus fluent assertions API with semantic labels for clear, robust tests.

## Gradle build

This project includes Gradle build files. To build locally:

1. If you have Gradle installed, run:

```bash
gradle build
```

2. To generate the Gradle wrapper (recommended) and then build:

```bash
gradle wrapper --gradle-version 9.2.1
./gradlew build
```

The `build.gradle` configures Groovy sources under `src/main/groovy` and Spock tests under `src/test/groovy`.

# jenkins-test-ops

> ⚠️ **Work in Progress**: This project is under active development and has not been released yet.

Stop testing Jenkins implementation details. JenkinsTestOps provides dependency injection and test doubles for pipeline operations. Test business logic, not string-matched shell commands. Plus fluent assertions API with semantic labels for clear, robust tests.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Gradle build

This project includes Gradle build files. To build locally:

1. If you have Gradle installed, run:

```bash
gradle build
```

## Gradle unittest

1. Run all tests (framework + examples)
```bash
gradle test
```

1. Run only example tests
```bash
gradle :examples:test
```

1. Run only framework tests
```bash
gradle :jenkins-test-ops:test
```

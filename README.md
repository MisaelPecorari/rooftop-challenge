# Rooftop Challenge

### Requirements
This project was built using Maven as dependency manager and JDK11, so please make sure you have them installed and well configured.

### How to run
At project's root and from the command line, we need to clean, install dependencies and compile the project: `mvn clean package -DskipTests`

Then, we can run one or multiple tests as follows:
1. Run all tests: `mvn test`
2. Run some tests with a mocked server: `mvn test -Dtest="BlockServiceTest"`
3. Run integration test with real server and default email: `mvn test -Dtest="BlockServiceIntegrationTest"`
4. Run integration test with real server and a custom email: `mvn test -Dtest="BlockServiceIntegrationTest" -Demail="your@email.com"`

# Rooftop Challenge

### How to run
First of all, we need to clean and install all the dependencies: `mvn clean install`

Then, we can run one or multiple tests as follows:
1. Run all tests: `mvn test`
2. Run some tests with a mocked server: `mvn test -Dtest="BlockServiceTest"`
3. Run integration test with real server and default email: `mvn test -Dtest="BlockServiceIntegrationTest"`
4. Run integration test with real server and a custom email: `mvn test -Dtest="BlockServiceIntegrationTest" -Demail="your@email.com"`
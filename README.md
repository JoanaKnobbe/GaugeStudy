# Gauge Training -  Sauce Demo

This are exercises where the main goal was:
1. To independently write, executes, and maintain Java-based Selenium tests integrated into Gauge. 
2. To become comfortable with XPath, POM, and handling common automation challenges.

## Task Description:
- [x] Combine the Java-based Selenium tests with Gauge specs and steps.

- [x] Write initial Gauge specs that call Selenium steps.

- [x] Run tests through Gauge to ensure they execute smoothly

### Background

You are tasked with creating an automated testing suite for the Sauce Demo e-commerce application (https://www.saucedemo.com/) using the Gauge testing framework. The application allows users to browse products, add them to cart, and complete purchases

*Using: Java, Selenium, Maven and Gauge.*

**Task 1: Create a simple test specification that:**


- Opens the Sauce Demo homepage

- Logs in using the standard user credentials

- Verifies the inventory page loads

- Validates the product sorting functionality

**Task 1 - Solution Structure:**
1. [Driver class to set the Chrome Driver](src/test/java/Driver.java)
2. [Gauge steps on saucedemoTest file](specs/saucedemoTest.spec)
3. [Step Implementation class](src/test/java/StepImplementation.java)

___

**Task 2: Test Organization and Data Management**

1. Create a test suite that demonstrates:

- Proper use of Gauge concepts
- Data-driven testing using Gauge data tables
- Parameter usage across multiple scenarios
- Appropriate step organization and reusability

**Task 2 - Solution Structure:**
1. [Driver class to set the Chrome Driver](src/test/java/Driver.java)
2. [Gauge steps on dataDrivenTest file](specs/dataDrivenTest.spec)
3. [Step Implementation class](src/test/java/StepImplementation.java)

## Instructions for running tests:
1. Run mvn clean install
2. Go to [Driver class](src/test/java/Driver.java) and manually replace your ChromeDrive location (line 11)
3. You can run tests directly at the IDE specs files, or
4. Run tests using CLI: 
```
gauge run
```
4. To run specific tests: 
```
gauge run [args] [flags]
```

More information at [Gauge documentation](https://docs.gauge.org/execution?os=null&language=java&ide=vscode)


# SauceDemoAutomation

A Selenium + TestNG + Cucumber (POM) framework for testing https://www.saucedemo.com.

## Quick Start
```bash
mvn clean test        # runs TestNG suite (testng.xml)
mvn -Dcucumber.filter.tags='@smoke' test  # run a Cucumber tag
```

## Tech
- Java 17, Maven
- Selenium 4, WebDriverManager
- TestNG, Cucumber 7
- Extent Reports 5
- Apache POI (Excel test data)

## Reports
- `reports/extent-reports` – Extent HTML
- `reports/cucumber-reports` – Cucumber JSON/HTML
- `reports/screenshots` – Failure screenshots

## Structure
Matches your requested layout.

Generated on 2025-09-04T04:09:47.

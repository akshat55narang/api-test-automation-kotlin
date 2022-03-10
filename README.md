[![Execute API Tests](https://github.com/akshat55narang/api-test-automation-kotlin/actions/workflows/pipeline.yml/badge.svg?branch=master&event=push)](https://github.com/akshat55narang/api-test-automation-kotlin/actions/workflows/pipeline.yml)

# api-test-automation-kotlin

### Overview
This test automation framework is designed to test fake REST Api https://www.instantwebtools.net/fake-rest-api
There are 9 different API endpoints (with authentication) covering most of the HTTP methods.

### Tools Used
1. Programming Language - Kotlin
2. Build - Maven
3. Framework Details - TestNG, Rest Assured
4. Reports - Surefire reports

### Test Artifact Location
Tests - src/test/kotlin <br/>
Report - target/surefire-reports/index.html

### How To Run
From the api-test-automation-kotlin directory 
- Default configuration - `mvn clean test`
- Override default configuration - `clean test -DdefaultUsername=api-user4@iwt.net -DdefaultPassword=b3z0nV0cLO`




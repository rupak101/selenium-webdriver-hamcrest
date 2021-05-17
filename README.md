# Automation Test Suite

<br/>This automated test suite covers both WEB and API mentioned in code challenge file.

# Tools, Framework,Programming Language used for WEB: 
   * Intellij IDE, maven, Selenium Webdriver, TestNG, Java 8+,Hamcrest, Page object model (POM)
   
# Tools, Framework,Programming Language used for API: 
   * Intellij IDE, maven, TestNG, Java 8+,Hamcrest, RestAssured
      
# Libraries Used for UI
* Selenium:
    * To incorporate web tests.
* TestNG:
    * To perform parallel execution of test.
    * To perform cross browser/platform test.

# Libraries Used for API
* TestNG:
    * To perform parallel execution of test.
    
* Client model architecture
    -RestAssured client consist all type of request (e.g GET,POST etc) and Model class(POJO class) contains the response to valid output.
    
* Schema validation:
  *  Validate the response by json schema validator.
  
* Email validation:  
  * Apache Commons validator used to validate the email address format.
  
# Development environment : 
   * All development and execution done on Mac OS.It should work on other OS(e.g windows) as well. 
 
# Features:
* Generation human readable allure report
    - HTML Reports are available in the "/target/allure-report" directory having details of each test case execution 
    and take screenshot of failed test cases.
* Encapsulation layers like test data, logic of tests, actions on web pages and so on
    - Page object model design pattern used to have a clean separation of layers consisting of test data, logic
    and actions on web pages.
* Configurator(via testng.xml file):
  * run tests in parallel mode;
    - Test cases executed in parallel with multiple threads.
  * ability to run tests for different browsers by configuring;
    - Test can run for chrome/firefox browsers using parameters in testng.xml file.
* Allure report: 
  *Integrate to defect tracking system by using @link
  *Test order by severity by using @Severity annotation.
  *Tests groups with @Epic, @Feature, and @Stories annotations.

# Steps to execute the project:
* Method to run in Terminal:
    * Go to project folder in the terminal or command line
    * Execute via terminal or command line by entering following command.
    
    Run the below command:
    ```
    mvn clean test
    ``` 
    Run the command to generate allure report and open it in a browser: 
    ```bash
    mvn allure:serve
    ```
  
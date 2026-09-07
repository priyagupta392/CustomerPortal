Customer Portal – Selenium Automation Framework
📌 Project Overview

This project is a UI Automation Testing Framework developed for the Customer Portal application.

The framework automates key customer journeys such as login, discovery, company search, stock selection, purchase flow, cart validation, portfolio verification, and other critical business scenarios.

The framework is designed using Java, Selenium WebDriver, TestNG, Maven, and Page Object Model (POM) to provide a maintainable and scalable automation solution.

🏗️ Automation Framework

The framework follows the Page Object Model (POM) design pattern.

Framework Components
Java – Programming language
Selenium WebDriver – Web UI automation
TestNG – Test execution and test management
Maven – Build and dependency management
PageFactory – Page element initialization
Properties files – Environment/configuration management
Extent Reports – Test execution reporting
WebDriverWait – Explicit waits for synchronization
Git/GitHub – Source code management
🛠️ Tech Stack
Technology	Purpose
Java	Programming Language
Selenium WebDriver	Web Automation
TestNG	Test Execution
Maven	Build & Dependency Management
Page Object Model	Framework Design
PageFactory	WebElement Initialization
Extent Reports	Execution Reporting
Git	Version Control
GitHub	Source Code Repository
📂 Project Structure
Cust_Portal
│
├── src
│   ├── main
│   │   └── java
│   │       ├── com.Base
│   │       ├── com.Pages
│   │       └── com.util
│   │
│   └── test
│       └── java
│           └── com.Tests
│
├── test-output
│
├── screenshots
│
├── pom.xml
├── testng.xml
├── config.properties
└── README.md

The exact package names can be updated according to the final project structure in the repository.

🧩 Page Object Model

The framework follows the Page Object Model design pattern.

Each application page has a dedicated Page Class containing:

WebElement locators
Page-specific methods
User actions
Validation methods

For example:

LoginPage
    ↓
DiscoveryPage
    ↓
CompanyDetailsPage
    ↓
PurchasePage
    ↓
CartPage
    ↓
PortfolioPage

This approach helps to:

Reduce code duplication
Improve maintainability
Separate test logic from page implementation
Make locator maintenance easier
Improve framework scalability
🧪 Test Scenarios Covered

The automation suite covers important Customer Portal business flows.

🔐 Login
Login with valid credentials
OTP validation
OTP field validation
OTP button validation
Invalid login scenarios
Login error message validation
🔎 Discovery
Verify Discovery page
Search for companies
Validate searched company
Validate company cards
Validate company information
Apply category/sector filters
Validate filtered results
Validate stock information
💰 Purchase Flow
Select investment/company
Validate stock price
Select quantity
Validate investment amount
Add investment to cart
Validate cart details
🛒 Cart
Validate selected stocks
Validate quantity
Validate investment value
Validate total investment value
Validate cart calculations
📊 Portfolio
Validate portfolio details
Validate investment values
Validate total portfolio value
Validate displayed calculations against expected values
⚙️ TestNG

TestNG is used as the test execution framework.

The framework uses TestNG features such as:

@Test
@BeforeMethod
@AfterMethod
Test priority
Test groups
dependsOnMethods
testng.xml

Example:

@Test(
    priority = 1,
    groups = {"login"},
    description = "Verify login with valid credentials"
)
public void loginWithValidCredentials() {
    // Test steps
}


## Reporting
Extent Reports are generated after test execution.

## How to Run
1. Clone the repository
2. Import as Maven project
3. Update configuration
4. Run testng.xml

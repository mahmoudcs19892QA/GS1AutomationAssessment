# Senior Software QC Automation Assessment

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.25.0-green.svg)](https://www.selenium.dev/)
[![REST-Assured](https://img.shields.io/badge/REST--Assured-5.5.6-blue.svg)](https://rest-assured.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red.svg)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-black.svg)](https://maven.apache.org/)

Automated test solution for the Senior Software QC Automation Assessment covering **GUI Web Automation** ([The Internet - Herokuapp](https://the-internet.herokuapp.com/)) and **RESTful API Automation** ([FakeRESTApi Bookstore](https://fakerestapi.azurewebsites.net)).

---

## 🛠️ Tech Stack & Design Patterns

- **Technologies**: Java 17, Selenium WebDriver (4.25.0), REST Assured (5.5.6), TestNG (7.10.2), Maven.
- **Design Patterns**: Page Object Model (POM) with `PageManager`, Service Object Model (SOM), Fluent Design.
- **Data-Driven Testing (DDT)**: Externalized test data using Apache POI (`books.xlsx`), JSON request payloads, and `config.properties`.
- **Synchronization**: Explicit waits (`WebDriverWait`) parameterized via configuration, completely avoiding `Thread.sleep`.

---

## 📁 Project Structure

```text
GS1AutomationAssessment/
├── pom.xml                               # Maven dependencies & Surefire configuration
├── testng.xml                            # Master TestNG suite (GUI + API execution)
├── README.md                             # Project documentation
├── src/
│   ├── main/java/com/mahmoud/
│   │   ├── manager/PageManager.java      # Lazy initialization for page objects
│   │   ├── pages/                        # POM: BasePage, HomePage, FileUpload, DynamicLoading
│   │   ├── services/BookService.java     # SOM: RestAssured API client with request logging
│   │   └── utils/                        # ConfigReader, ExcelReader (POI), JsonReader
│   └── test/
│       ├── java/com/mahmoud/
│       │   ├── api/BooksApiTest.java     # API CRUD test suite with sequential priorities
│       │   ├── base/BaseTest.java        # WebDriver lifecycle (setup/maximize/safe teardown)
│       │   ├── data/                     # Externalized TestNG DataProviders
│       │   └── tests/                    # GUI tests: FileUploadTest, DynamicLoadingTest
│       └── resources/
│           ├── config.properties         # Environment URLs and test parameters
│           └── test-data/                # books.xlsx, books-create.json, books-update.json, sample.jpg
```

---

## 🔄 Test Execution Flow & Order

Tests are orchestrated sequentially via `testng.xml` (`preserve-order="true"`):

### 1. GUI Tests (Runs First)
- **`FileUploadTest`**: Uploads `sample.jpg` and asserts both the confirmation header (`File Uploaded!`) and the file name.
- **`DynamicLoadingTest`**: Navigates to Example 2, clicks Start, waits explicitly for the element to render, and verifies `"Hello World!"`.

### 2. API Tests (Runs Second - Sequential Priorities)
| Priority | Method | Type | Description |
|---|---|---|---|
| **1** | `getAllBooks()` | Happy Path | Validates status 200, content-type JSON, and schema fields. |
| **2** | `getBookById(id)` | Happy Path (DDT) | Validates status 200 using valid IDs from Excel (`Books` sheet). |
| **3** | `getBookByIdWithInvalidId(id)` | Negative (DDT) | Validates status 404 and error body using invalid IDs (`invalidBooks` sheet). |
| **4** | `createBook()` | Happy Path | Posts new book using `books-create.json` payload. |
| **5** | `updateBook()` | Happy Path | Updates book ID 1 using `books-update.json` payload. |
| **6** | `deleteBook()` | Happy Path | Deletes book ID 1 and validates status 200. |

---

## 🔍 FakeRESTApi Behavior Notes

Per the assessment requirements:
- **Stateless Mock**: `POST`, `PUT`, and `DELETE` operations simulate success (`200 OK`) but do not persist mutations in the backend database.
- **Non-Standard Status Codes**: `POST` returns `200 OK` (with `id: 0`) instead of `201 Created`; `DELETE` returns `200 OK` instead of `204 No Content`.
- **Error Contract**: Non-existent IDs return `404 Not Found` adhering to RFC 7807 problem details, validated in `getBookByIdWithInvalidId`.

---

## 🚀 How to Run the Tests

### Run All Tests (GUI + API in defined order):
- **Via Maven Terminal**:
  ```bash
  mvn clean test
  ```
- **Via IntelliJ IDEA**:
  Right-click `testng.xml` -> **Run '...testng.xml'**.

### Run Selectively:
```bash
# GUI tests only
mvn test -Dtest=FileUploadTest,DynamicLoadingTest

# API tests only
mvn test -Dtest=BooksApiTest
```

---

## 📊 Test Reports

After test execution, Surefire HTML reports are generated at:
- **TestNG Detailed Report**: `target/surefire-reports/index.html`
- **Emailable Summary Report**: `target/surefire-reports/emailable-report.html`

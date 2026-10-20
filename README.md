# Concurrent CSV Data Processor

A Java application built with **Java 17** and **Spring Boot** that processes employee data stored in CSV files using **multithreading and concurrency control**.

The application reads employee records, calculates salary increments based on years of service, employee roles, and project completion percentages, and exports the updated salaries into a new CSV file.

## Features

- **CSV File Reading:** Read employee records using OpenCSV.
- **Salary Calculation:** Calculate salary increases based on predefined business rules.
- **Thread Pooling:** Process employee records concurrently using `ExecutorService`.
- **Concurrency Control:** Use `ReentrantLock` and `Semaphore` to manage access to resources.
- **Bonus Salary Increment:** Apply a 1.5× multiplier for employees who completed more than 80% of their projects.
- **CSV Export:** Export updated employee records, including their original salaries, new salaries, and processing thread names.
- **Spring Boot Integration:** Execute the processing workflow using `CommandLineRunner`.

---

## Technologies Used

| Technology | Purpose |
|---|---|
| Java 17 | Main programming language |
| Spring Boot | Application framework |
| Maven | Build and dependency management |
| OpenCSV | CSV reading and writing |
| ExecutorService | Thread pool management |
| Future | Retrieving results from concurrent tasks |
| ReentrantLock | Controlling CSV file access |
| Semaphore | Limiting concurrent processing operations |
| BigDecimal | Precise salary calculations |

---

## Salary Increment Rules

The salary calculation depends on the employee's project completion percentage, years of service, and role.

### 1. Project Completion

| Completion Percentage | Salary Increment |
|---|---|
| Below 60% | No increase |
| 60% to 80% | Standard increase |
| Above 80% | Standard increase × 1.5 |


### 2. Years of Service

Employees receive an additional **2% salary increase for each completed year of service**.

| Years Worked | Increase |
|---|---|
| Less than 1 year | 0% |
| 1 year | 2% |
| 3 years | 6% |
| 5 years | 10% |
| 10 years | 20% |

Partial years are not counted.

### 3. Role-Based Increase

| Role | Increase |
|---|---|
| Director | 5% |
| Manager | 2% |
| Employee | 1% |

---
### 4. Salary Calculation Formula

```text
Experience Increase = Completed Years × 2%

Total Increase = Experience Increase + Role Increase

New Salary = Current Salary × (1 + Total Increase / 100)
```

For employees with project completion above 80%:

```text
Total Increase = (Experience Increase + Role Increase) × 1.5
```

Employees with project completion below 60% receive no increase.

### Example Calculation

Employee details:

- Name: Alice
- Current Salary: 52,000
- Joined Date: 2019-05-12
- Calculation Date: 2026-10-20
- Role: Employee
- Project Completion: 80%

```text
Completed Years = 7

Experience Increase = 7 × 2% = 14%

Role Increase = 1%

Total Increase = 14% + 1% = 15%

New Salary = 52,000 × 1.15

New Salary = 59,800
```

---

## Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/miniproject/dataprocessor/
│   │       ├── DataprocessorApplication.java
│   │       ├── DataProcessorRunner.java
│   │       ├── config/
│   │       │   └── ExecutorConfig.java
│   │       ├── model/
│   │       │   ├── Employee.java
│   │       │   ├── EmployeeResult.java
│   │       │   └── Role.java
│   │       └── service/
│   │           ├── CsvReaderService.java
│   │           ├── CsvWriterService.java
│   │           ├── SalaryCalculatorService.java
│   │           └── EmployeeProcessingService.java
│   └── resources/
│       ├── application.properties
│       └── test_employees.csv
├── test/
│   └── java/
output/
└── processed_employees.csv

pom.xml
README.md
```

---

## Application Workflow

```text
Start Spring Boot Application
            |
            v
    DataProcessorRunner
            |
            v
    Read test_employees.csv
            |
            v
    Convert Rows to Employee Objects
            |
            v
    Submit Tasks to ExecutorService
            |
            v
    Semaphore Controls Processing
            |
            v
    Calculate Updated Salaries
            |
            v
    Collect Results Using Future
            |
            v
    ReentrantLock Protects CSV Writing
            |
            v
    Export processed_employees.csv
            |
            v
        Processing Complete
```
## Testing and Verification

The application was manually tested using the provided CSV file containing 30 employee records.

### Processing Verification

The application successfully:

1. Loaded all 30 employee records.
2. Processed employee salaries through the configured thread pool.
3. Applied salary increments according to the business rules.
4. Applied the bonus multiplier to eligible employees.
5. Generated the output CSV containing all 30 employee results.

### Sample Results

Using October 20, 2026 as the calculation date:

| Employee | Original Salary | Updated Salary |
|---|---:|---:|
| Alice | 52,000.00 | 59,800.00 |
| Bob | 68,000.00 | 77,520.00 |
| Charlie | 75,000.00 | 86,250.00 |
| Diana | 60,000.00 | 60,000.00 |
| Eve | 54,000.00 | 66,150.00 |

## Project Outcomes

The project demonstrates practical experience with:

- Reading and writing CSV files.
- Applying salary increment business rules.
- Using `BigDecimal` for accurate financial calculations.
- Implementing multithreading with `ExecutorService`.
- Retrieving concurrent results using `Future`.
- Managing shared resources with `ReentrantLock`.
- Limiting concurrent access using `Semaphore`.
- Organizing application logic into reusable Spring Boot services.

Because individual salary calculations are lightweight, concurrent processing does not necessarily guarantee faster execution for small datasets.

The main purpose of multithreading in this project is to demonstrate thread management and synchronization techniques.

---

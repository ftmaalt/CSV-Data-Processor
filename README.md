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

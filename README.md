# Concurrent CSV Data Processor

A Java application built using Java 17 and Spring Boot that processes employee data stored in CSV files concurrently.

The application calculates salary increments based on predefined criteria (See below) , including employee roles, years of service, and project completion percentages. It aims to utilize concurrency and multithreading techniques to improve processing efficiency while maintaining data integrity.

---
## Salary Increment Rules

| Criteria | Salary Adjustment |
|----------|-------------------|
| Project completion below 60% | No salary increase |
| Director | 5% base increment |
| Manager | 2% base increment |
| Employee | 1% base increment |
| Each completed year of service | Additional 2% |
| Project completion above 80% (Bonus) | 1.5× total increment |

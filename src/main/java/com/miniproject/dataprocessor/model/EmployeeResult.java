package com.miniproject.dataprocessor.model;

import java.math.BigDecimal;

public record EmployeeResult(
        int id,
        String name,
        BigDecimal oldSalary,
        BigDecimal newSalary,
        String threadName
) {
}

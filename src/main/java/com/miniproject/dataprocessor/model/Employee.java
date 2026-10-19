package com.miniproject.dataprocessor.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record Employee(
    int emp_id,
    String name,
    BigDecimal current_salary,
    LocalDate join_date,
    Role role,
    double project_percent
){}

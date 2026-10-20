package com.miniproject.dataprocessor.service;

import com.miniproject.dataprocessor.model.Employee;
import com.miniproject.dataprocessor.model.Role;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class SalaryCalculatorService {

    public BigDecimal newSalaryCalculator(Employee employee, LocalDate calculationDate){

        if (employee.project_percent()< 0.6){
            return employee.current_salary();
        }

        long year = ChronoUnit.YEARS.between(employee.join_date(), calculationDate);
        BigDecimal yearlyIncrease=BigDecimal.ZERO;
        if (year> 0){
            yearlyIncrease= BigDecimal.valueOf(year).multiply(new BigDecimal("0.02"));
        }

        BigDecimal roleIncrease = switch (employee.role()){
            case DIRECTOR -> new BigDecimal("0.05");
            case MANAGER -> new BigDecimal("0.02");
            case EMPLOYEE -> new BigDecimal("0.01");
        };
        BigDecimal increaseRate = yearlyIncrease.add(roleIncrease);

        if (employee.project_percent() > 0.8){
            increaseRate = increaseRate.multiply(new BigDecimal("1.5"));
        }

        BigDecimal multiplier = BigDecimal.ONE.add(increaseRate);
        BigDecimal newSalary = employee.current_salary().multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
        return newSalary;
    }

}

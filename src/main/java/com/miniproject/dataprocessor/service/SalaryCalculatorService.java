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
            System.out.print("This employee doesn't get a salary increase..\nCurrent Salary: $");
            return employee.current_salary();
        }

        long year = ChronoUnit.YEARS.between(employee.join_date(), calculationDate);
        BigDecimal yearlyIncrease=BigDecimal.ZERO;
        if (year> 0){
            yearlyIncrease=new BigDecimal(year * (2/100));
        }

        BigDecimal roleIncrease = switch (employee.role()){
            case MANAGER -> new BigDecimal("0.05");
            case DIRECTOR -> new BigDecimal("0.02");
            case EMPLOYEE -> new BigDecimal("0.01");
        };
        BigDecimal increaseRate = yearlyIncrease.add(roleIncrease);
        BigDecimal multiplier = BigDecimal.ONE.add(increaseRate);
        BigDecimal newSalary = employee.current_salary().multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
        System.out.print("New Employee Salary: $");
        return newSalary;
    }

}

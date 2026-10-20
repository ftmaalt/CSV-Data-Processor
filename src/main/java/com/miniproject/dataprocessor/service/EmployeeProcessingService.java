package com.miniproject.dataprocessor.service;

import com.miniproject.dataprocessor.model.Employee;
import com.miniproject.dataprocessor.model.EmployeeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class EmployeeProcessingService {
    @Autowired
    private ExecutorService executor;
    @Autowired
    private SalaryCalculatorService calculatorService;

    public List<EmployeeResult> processNewSalaries(List<Employee> employees){
        return  null;
    }

}

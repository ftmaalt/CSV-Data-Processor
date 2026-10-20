package com.miniproject.dataprocessor.service;

import com.miniproject.dataprocessor.model.Employee;
import com.miniproject.dataprocessor.model.EmployeeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

@Service
public class EmployeeProcessingService {
    @Autowired
    private ExecutorService executor;
    @Autowired
    private SalaryCalculatorService calculatorService;

    private final Semaphore semaphore = new Semaphore(2);

    public List<EmployeeResult> processNewSalaries(List<Employee> employees) throws ExecutionException, InterruptedException {
        LocalDate calculationDate = LocalDate.now();

        List<Future<EmployeeResult>> futures = new ArrayList<>();

        for (Employee employee : employees) {
            Future<EmployeeResult> future = executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " is waiting..");
                semaphore.acquire();
                try {
                    System.out.println(Thread.currentThread().getName() +" Acquired Permit ");
                    BigDecimal newSalary = calculatorService.newSalaryCalculator(employee, calculationDate);

                    return new EmployeeResult(employee.emp_id(), employee.name(), employee.current_salary(), newSalary, Thread.currentThread().getName());
                } finally {
                    semaphore.release();
                    System.out.println("Released,, Thread: "+ Thread.currentThread().getName());
                }
            });
            futures.add(future);
        }

        List<EmployeeResult> results = new ArrayList<>();
        for (Future<EmployeeResult> future : futures){
            results.add(future.get());
        }
        return results;
    }

}

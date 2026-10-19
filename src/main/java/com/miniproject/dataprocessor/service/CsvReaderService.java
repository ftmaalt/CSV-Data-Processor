package com.miniproject.dataprocessor.service;

import com.miniproject.dataprocessor.model.Employee;
import com.miniproject.dataprocessor.model.Role;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

@Service
public class CsvReaderService {
    public static List<Employee> getEmployees(String file){
        List<Employee> employees = new ArrayList<>();

        try(FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader)){

            String[] nextRecord;
            int lineNumber= 0;

            while ((nextRecord = csvReader.readNext())!= null){
                lineNumber++;
                try {
                    int emp_id= Integer.parseInt(nextRecord[0].trim());
                    String name = nextRecord[1].trim();
                    BigDecimal current_salary= new BigDecimal(nextRecord[2].trim());
                    LocalDate join_date=  LocalDate.parse(nextRecord[3].trim());
                    Role role = Role.valueOf(nextRecord[4].trim().toUpperCase());
                    double project_percent = Double.parseDouble(nextRecord[5].trim());

                    Employee employee= new Employee(emp_id, name, current_salary, join_date, role, project_percent);
                    employees.add(employee);
            } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
        }
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}


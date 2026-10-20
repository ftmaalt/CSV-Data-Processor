package com.miniproject.dataprocessor;

import com.miniproject.dataprocessor.model.Employee;
import com.miniproject.dataprocessor.model.EmployeeResult;
import com.miniproject.dataprocessor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class DataProcessorRunner implements CommandLineRunner {
    @Autowired
    private CsvReaderService csvReader;
    @Autowired
    private EmployeeProcessingService processor;
    @Autowired
    private CsvWriterService csvWriter;

    @Override
    public void run(String... args) throws Exception {
        String inputFile = "src/main/resources/test_employees.csv";

        String outputFile = "output/processed_employees.csv";

        List<Employee> employees = csvReader.getEmployees(inputFile);
        System.out.println("Employees loaded: "+ employees.size());

        List<EmployeeResult> processedEmployees =processor.processNewSalaries(employees);
        Files.createDirectories(Path.of("output"));

        csvWriter.writeResults(outputFile, processedEmployees);
        System.out.println("Processed Employees: "+ processedEmployees.size());
    }
}

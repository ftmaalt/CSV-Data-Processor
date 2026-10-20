package com.miniproject.dataprocessor.service;

import com.miniproject.dataprocessor.model.EmployeeResult;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CsvWriterService {
    public void writeResults(String filePath, List<EmployeeResult> employeeResults) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath); CSVWriter writer = new CSVWriter(fileWriter)) {
            String[] header = {"Employee ID", "Name", "Old Salary", "New Salary", "Processed By"};
            writer.writeNext(header);

            for (EmployeeResult result : employeeResults) {
                String[] empDate = {
                        String.valueOf(result.id()),
                        result.name(),
                        result.oldSalary().toPlainString(),
                        result.newSalary().toPlainString(),
                        result.threadName()
                };
                writer.writeNext(empDate);
            }
        }
    }
}
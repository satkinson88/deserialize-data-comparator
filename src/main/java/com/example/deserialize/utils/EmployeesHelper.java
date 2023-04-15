package com.example.deserialize.utils;

import com.example.deserialize.model.Employees;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesHelper {

    public List<Employees> parseCsvForEmployees(String fileName) {
        BeanListProcessor<Employees> rowProcessor = new BeanListProcessor<>(Employees.class);
        try (Reader inputReader = new InputStreamReader(new FileInputStream(
                "src/test/java/resources/expectedOutput/" + fileName), "UTF-8")) {
            CsvParserSettings parserSettings = new CsvParserSettings();
            parserSettings.setProcessor(rowProcessor);
            parserSettings.setHeaderExtractionEnabled(true);
            CsvParser parser = new CsvParser(parserSettings);
            parser.parse(inputReader);
            return rowProcessor.getBeans();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowProcessor.getBeans();
    }

    public List<String> getEmployeesValues(Employees employees){
        List<String> employeeValues = new ArrayList<>();
        employeeValues.add(String.valueOf(employees.getEmployee_id()));
        employeeValues.add(employees.getFirst_name());
        employeeValues.add(employees.getLast_name());
        employeeValues.add(employees.getEmail());
        employeeValues.add(employees.getHire_date());
        employeeValues.add(employees.getDepartment());
        employeeValues.add(employees.getGender());
        employeeValues.add(String.valueOf(employees.getSalary()));
        return employeeValues;
    }
}

package com.example.deserialize.features;

import com.example.deserialize.database.DatabaseDAO;
import com.example.deserialize.database.DatabaseDAOImpl;
import com.example.deserialize.database.DatabaseInitializer;
import com.example.deserialize.model.Departments;
import com.example.deserialize.model.Employees;
import com.example.deserialize.utils.EmployeesHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCases {

    DatabaseDAO databaseDAO;
    private Logger logger = Logger.getLogger(TestCases.class.getName());

    @BeforeAll
    public void setupDatabaseConnection(){
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        databaseInitializer.setJdbcTemplate();
        JdbcTemplate jdbcTemplate = databaseInitializer.getJdbcTemplate();
        DatabaseDAOImpl databaseDAOImpl = new DatabaseDAOImpl(jdbcTemplate);
        this.databaseDAO = databaseDAOImpl;
    }

    @Test
    public void countMaleEmployees(){
        int actualMaleEmployeeCount = databaseDAO.countEmployeesByGender("M");
        Assertions.assertEquals(499, actualMaleEmployeeCount);
        logger.info("Expected count matches actual value of " + actualMaleEmployeeCount);
    }

    @Test
    public void countFemaleEmployees(){
        int actualFemaleEmployeeCount = databaseDAO.countEmployeesByGender("F");
        Assertions.assertEquals(501, actualFemaleEmployeeCount);
        logger.info("Expected count matches actual value of " + actualFemaleEmployeeCount);
    }

    @Test
    public void verifyRegionAndCountryById(){
        Map<String, Object> regionAndCountry;
        regionAndCountry = databaseDAO.getRegionAndCountryById(3);
        String region = (String) regionAndCountry.get("region");
        logger.info("Region: " + region);
        String country = (String) regionAndCountry.get("country");
        logger.info("Country: " + country);
        Assertions.assertEquals("Northwest", region);
        Assertions.assertEquals("United States", country);
    }

    @Test
    public void verifyOrderOfDepartments(){
        List<Departments> departments;
        departments = databaseDAO.getDepartmentsInAlphabeticalOrder();
        String department = departments.get(17).getDepartment();
        logger.info("Department: " + department);
        String division = departments.get(17).getDivision();
        logger.info("Division: " + division);
        Assertions.assertEquals("Music", department);
        Assertions.assertEquals("Entertainment", division);
    }

    @Test
    public void verifySalariesByDepartment() {
        List<?> employeesActual = databaseDAO.getSumOfSalariesByGrouping("department");
        EmployeesHelper employeesHelper = new EmployeesHelper();
        List<?> employeesExpected = employeesHelper.parseCsvForEmployees(
                "employeesSumSalariesGroupByDepartment.csv");
        for (int i = 0; i < employeesActual.size(); i++) {
            Employees employeesExpectedRow = (Employees) employeesExpected.get(i);
            Employees employeesActualRow = (Employees) employeesActual.get(i);
            List<String> stringEmployeesExpectedRow = employeesHelper.getEmployeesValues(employeesExpectedRow);
            logger.info(stringEmployeesExpectedRow.toString());
            List<String> stringEmployeesActualRow = employeesHelper.getEmployeesValues(employeesActualRow);
            logger.info(stringEmployeesActualRow.toString());
            Assertions.assertEquals(stringEmployeesExpectedRow, stringEmployeesActualRow);
        }
    }
}

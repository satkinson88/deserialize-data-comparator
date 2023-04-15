package com.example.deserialize.database;

import com.example.deserialize.model.Departments;
import com.example.deserialize.model.Employees;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DatabaseDAOImpl implements DatabaseDAO {

    JdbcTemplate jdbcTemplate;
    private Logger logger = Logger.getLogger(DatabaseDAOImpl.class.getName());

    public DatabaseDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //queryForObject (used for single row of results)
    @Override
    public int countEmployeesByGender(String gender) {
        int employeeCount = 0;
        try {
            String sql = "select count(*) from employees e where e.gender=?";
            employeeCount = jdbcTemplate.queryForObject(sql, Integer.class, gender);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return employeeCount;
    }

    //queryForList (used to return a map of the results for each row)
    public Map<String, Object> getRegionAndCountryById(int region_id) {
        String sql = "select * from regions r where r.region_id = ?";
        List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(sql, region_id);
        Map<String, Object> regionDetails = resultSet.get(0);
        return regionDetails;
    }

    //Querying and populating a number of domain objects
    public List<Departments> getDepartmentsInAlphabeticalOrder() {
        String sql = "select * from departments d order by d.department";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Departments setDepartments = new Departments();
            setDepartments.setDepartment(rs.getString("department"));
            setDepartments.setDivision(rs.getString("division"));
            return setDepartments;
        });
    }

    //Querying and populating a number of domain objects with param (symbol ? can only be used in the where clause)
    public List<Employees> getSumOfSalariesByGrouping(String groupBy){
        String sql = "select department, sum(salary) as salary from employees e " +
                "group by e." + groupBy + " order by salary desc;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Employees employees = new Employees();
            employees.setDepartment(rs.getString("department"));
            employees.setSalary(rs.getInt("salary"));
            return employees;
        });
    }
}

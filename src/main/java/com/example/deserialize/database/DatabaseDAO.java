package com.example.deserialize.database;

import com.example.deserialize.model.Departments;

import java.util.List;
import java.util.Map;

public interface DatabaseDAO {

    public int countEmployeesByGender(String gender);

    public Map<String, Object> getRegionAndCountryById(int region_id);

    public List<Departments> getDepartmentsInAlphabeticalOrder();

    public List<?> getSumOfSalariesByGrouping(String groupBy);

}

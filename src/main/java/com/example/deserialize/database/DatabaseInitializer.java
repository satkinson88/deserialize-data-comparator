package com.example.deserialize.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.logging.Logger;

public class DatabaseInitializer {

    private Logger logger = Logger.getLogger(DatabaseInitializer.class.getName());
    private JdbcTemplate jdbcTemplate;
    private DriverManagerDataSource dataSource = new DriverManagerDataSource();

    public DriverManagerDataSource driverManagerDataSource() {
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(System.getProperty("jdbc.url"));
        dataSource.setUsername(System.getProperty("user"));
        dataSource.setPassword(System.getProperty("password"));
        return dataSource;
    }

    public void setJdbcTemplate() {
        driverManagerDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate(){
        return this.jdbcTemplate;
    }

}

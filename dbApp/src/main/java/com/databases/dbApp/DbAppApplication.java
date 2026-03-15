package com.databases.dbApp;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class DbAppApplication implements CommandLineRunner {
    private DataSource dataSource;

    public DbAppApplication(DataSource db) {
        this.dataSource = db;
    }

    public static void main(String[] args) {
        SpringApplication.run(DbAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Database: " + dataSource.toString());
        final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
        restTemplate.execute("select 1");
    }
}

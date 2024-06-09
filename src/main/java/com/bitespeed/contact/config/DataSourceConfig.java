package com.bitespeed.contact.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://dpg-cpim7821hbls73bjahg0-a.singapore-postgres.render.com/contact_to2v?currentSchema=users");
        config.setUsername("root");
        config.setPassword("lryfCGsXvaUdJ0OlUCrO4wg2o3E2sIuQ");

        return new HikariDataSource(config);
    }
}

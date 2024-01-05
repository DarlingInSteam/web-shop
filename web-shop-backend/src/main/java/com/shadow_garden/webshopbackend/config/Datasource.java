package com.shadow_garden.webshopbackend.config;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

public class Datasource {
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5431/web-shop")
                .username("admin")
                .password("admin")
                .build();
    }
}

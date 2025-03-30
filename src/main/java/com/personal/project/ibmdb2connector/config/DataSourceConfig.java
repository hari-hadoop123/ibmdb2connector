package com.personal.project.ibmdb2connector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "ibmdb2Datasource")
    @ConfigurationProperties(prefix = "spring.datasource.ibmdb2")
    public DataSource ibmdb2Datasource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "postgresDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource postgresDataSource(){
        return new DriverManagerDataSource();
    }


}



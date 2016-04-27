package org.ka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;

public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        throw new NotImplementedException();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        throw new NotImplementedException();
    }
}

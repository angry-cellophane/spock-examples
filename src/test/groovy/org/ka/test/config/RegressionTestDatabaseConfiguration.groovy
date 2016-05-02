package org.ka.test.config

import groovy.sql.Sql
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType

class RegressionTestDatabaseConfiguration {

    @Bean
    Sql sql() {
        new Sql(dataSource: dataSource())
    }

    @Bean(destroyMethod = 'shutdown')
    EmbeddedDatabase dataSource() {
        new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/sql/reg-test-init.sql")
                .build()
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        new JdbcTemplate(dataSource: dataSource())
    }
}

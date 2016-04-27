package org.ka.test.config

import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType

import javax.sql.DataSource

class EmbeddedDatabaseConfiguration {

    @Bean(destroyMethod = 'shutdown')
    EmbeddedDatabase dataSource() {
        new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/sql/init.sql")
                .build()
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        new JdbcTemplate(dataSource: dataSource())
    }
}

package org.ka.test.integration

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.ka.config.TradeProcessorConfiguration
import org.ka.test.config.EmbeddedDatabaseConfiguration
import org.ka.trades.TradeProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.JdbcUtils
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.jdbc.JdbcTestUtils

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = [ EmbeddedDatabaseConfiguration.class, TradeProcessorConfiguration.class ])
class IntegrationTest {

    @Autowired
    JdbcTemplate jdbcTemplate
    @Autowired
    TradeProcessingService tradeService;

    @Test void runTest() {
        Assert.assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "trades"));

    }
}

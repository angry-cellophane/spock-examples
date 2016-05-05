package org.ka.test.integration

import org.junit.runner.RunWith
import org.ka.config.TradeProcessorConfiguration
import org.ka.test.config.EmbeddedDatabaseConfiguration
import org.ka.trades.service.TradeProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

import java.sql.ResultSet

@Test
@ContextConfiguration(classes = [EmbeddedDatabaseConfiguration, TradeProcessorConfiguration])
class TestngIntegrationTest extends AbstractTestNGSpringContextTests {

    @Autowired JdbcTemplate jdbcTemplate
    @Autowired TradeProcessingService tradeService

    @Test(dataProvider = 'data')
    void test(String tradeInfo, String result) {
        tradeService.process(tradeInfo)

        def row = jdbcTemplate.queryForObject('select * from TRADES_PROCESSING where trade_info = ?',
                [tradeInfo] as Object[],
                { ResultSet rs, int rowNum ->
                    return [
                            timestamp   : rs.getTimestamp('ts').toInstant().toEpochMilli(),
                            tradeInfo   : rs.getString('trade_info'),
                            result      : rs.getString('result'),
                            errorMessage: rs.getString('error_message')
                    ]
                })

        Assert.assertEquals(tradeInfo, row['tradeInfo'])
        Assert.assertEquals(result, row['result'])
        Assert.assertNull(row['errorMessage'])
    }

    @DataProvider
    static Object[][] data() {
        [
                [ 'id=132|type=Sell|legalEntityId=1234', 'SUCCESS' ],
                [ 'id=234|type=Buy|legalEntityId=43321', 'SUCCESS' ]
        ]
    }
}

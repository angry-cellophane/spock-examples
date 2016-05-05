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
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterTest
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

import java.lang.reflect.Method
import java.sql.ResultSet

@Test(dataProvider = 'data')
@ContextConfiguration(classes = [EmbeddedDatabaseConfiguration, TradeProcessorConfiguration])
class TestngIntegrationTest extends AbstractTestNGSpringContextTests {

    @Autowired JdbcTemplate jdbcTemplate
    @Autowired TradeProcessingService tradeService

    void 'process correct trade info'(String tradeInfo, String result) {
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

    void 'process incorrect trade info'(String tradeInfo, String result, String errorMessage) {
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
        Assert.assertEquals(errorMessage, row['errorMessage'])
    }

    @AfterMethod
    void cleanup() {
        jdbcTemplate.execute('delete from TRADES')
        jdbcTemplate.execute('delete from TRADES_PROCESSING')
    }

    @DataProvider
    static Object[][] data(Method m) {
        [
                [ 'id=132|type=Sell|legalEntityId=1234', 'SUCCESS' ],
                [ 'id=234|type=Buy|legalEntityId=43321', 'SUCCESS' ],
                [ 'id=456|type=Sell', 'FAILURE', 'java.lang.RuntimeException: legalEntityId is empty in id=456|type=Sell' ],
                [ 'id=456|legalEntityId=43245', 'FAILURE', 'java.lang.RuntimeException: type is empty in id=456|legalEntityId=43245' ]
        ].findAll { it.size() == m.parameterCount }
    }
}

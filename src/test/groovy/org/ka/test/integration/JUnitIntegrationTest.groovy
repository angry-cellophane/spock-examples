package org.ka.test.integration

import org.codehaus.groovy.runtime.callsite.CallSite
import org.junit.After
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.ka.config.TradeProcessorConfiguration
import org.ka.test.config.EmbeddedDatabaseConfiguration
import org.ka.trades.service.TradeProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.jdbc.JdbcTestUtils

import java.sql.ResultSet

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = [EmbeddedDatabaseConfiguration.class, TradeProcessorConfiguration.class])
class JUnitIntegrationTest {

    @Autowired
    JdbcTemplate jdbcTemplate
    @Autowired
    TradeProcessingService tradeService

    @Test
    void processOneCorrectTradeInfo() {
        Assert.assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "trades"));

        String tradeInfo = "id=1|type=Sell|legalEntityId=1234";
        tradeService.process(tradeInfo);

        def row = jdbcTemplate.queryForObject('select * from TRADES_PROCESSING where trade_info = ?',
                [tradeInfo] as Object[],
                { ResultSet rs, int rowNum ->
                    [
                            timestamp   : rs.getTimestamp('ts').toInstant().toEpochMilli(),
                            tradeInfo   : rs.getString('trade_info'),
                            result      : rs.getString('result'),
                            errorMessage: rs.getString('error_message')
                    ]
                })

        Assert.assertEquals(tradeInfo, row['tradeInfo'])
        Assert.assertEquals('SUCCESS', row['result'])
        Assert.assertNull(row['errorMessage'])
    }

    @Test
    void processOneCorrectTradeInfoWithOptionalField() {
        String tradeInfo = "id=2|type=Buy|legalEntityId=2345|currency=EUR";
        tradeService.process(tradeInfo);

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
        Assert.assertEquals('SUCCESS', row['result'])
        Assert.assertNull(row['errorMessage'])
    }

    @Test
    void processInCorrectTradeInfo() {
        String tradeInfo = "id=3|type=Sell|legalEntityId=3456|currency";
        tradeService.process(tradeInfo);

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
        Assert.assertEquals('FAILURE', row['result'])
        Assert.assertNotNull(row['errorMessage'])
    }

    @Test
    void test() {
        CallSite[] var3 = $getCallSiteArray();
        println var3
    }

    @After
    public void cleanup() {
        jdbcTemplate.update("delete from TRADES_PROCESSING");
        jdbcTemplate.update("delete from TRADES");
    }
}

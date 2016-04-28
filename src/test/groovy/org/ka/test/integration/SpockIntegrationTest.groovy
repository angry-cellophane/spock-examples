package org.ka.test.integration

import groovy.sql.Sql
import org.ka.config.TradeProcessorConfiguration
import org.ka.test.config.EmbeddedDatabaseConfiguration
import org.ka.trades.service.TradeProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [EmbeddedDatabaseConfiguration.class, TradeProcessorConfiguration.class])
class SpockIntegrationTest extends Specification {

    @Autowired
    TradeProcessingService tradeService
    @Autowired
    Sql sql

    @Unroll
    def 'process a tradeInfo: #tradeInfo'() {
        when:
        tradeService.process(tradeInfo)
        then:
        def row = sql.firstRow("select trade_info, result, error_message from TRADES_PROCESSING where trade_info = ?", tradeInfo)
        and:
        row.intersect(result) == result
        where:
        tradeInfo                                          || result
        'id=1|type=Buy|legalEntityId=1234'                 || [TRADE_INFO: 'id=1|type=Buy|legalEntityId=1234', RESULT: 'SUCCESS', ERROR_MESSAGE: null]
        'id=2|type=Sell|legalEntityId=2345|currency=EUR'   || [TRADE_INFO: 'id=2|type=Sell|legalEntityId=2345|currency=EUR', RESULT: 'SUCCESS', ERROR_MESSAGE: null]
        'id=3|type=Sell|legalEntityId=BigBank123|currency' || [TRADE_INFO: 'id=3|type=Sell|legalEntityId=BigBank123|currency', RESULT: 'FAILURE']
        'id=4|legalEntityId=SmallBank456'                  || [TRADE_INFO: 'id=4|legalEntityId=SmallBank456', RESULT: 'FAILURE', ERROR_MESSAGE: 'java.lang.RuntimeException: type is empty in id=4|legalEntityId=SmallBank456']
    }

    def cleanup() {
        sql.call('delete from TRADES')
        sql.call('delete from TRADES_PROCESSING')
    }

}

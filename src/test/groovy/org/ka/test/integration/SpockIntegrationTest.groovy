package org.ka.test.integration

import groovy.sql.Sql
import org.ka.config.TradeProcessorConfiguration
import org.ka.test.config.EmbeddedDatabaseConfiguration
import org.ka.trades.service.TradeProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [EmbeddedDatabaseConfiguration.class, TradeProcessorConfiguration.class])
class SpockIntegrationTest extends Specification {

    @Autowired TradeProcessingService tradeService
    @Autowired Sql sql

    @Unroll
    def 'process a correct tradeInfo: #tradeInfo'() {
        when:
        tradeService.process(tradeInfo)
        then:
        def row = sql.firstRow("select trade_info, result, error_message from TRADES_PROCESSING where trade_info = ?", tradeInfo) as Map
        and:
        row.intersect(result) == result
        where:
        tradeInfo || result
        'id=1|type=Bond|legalEntityId=1234' || [ TRADE_INFO: 'id=1|type=Bond|legalEntityId=1234', RESULT: 'SUCCESS', ERROR_MESSAGE: null]
        'id=2|type=Converts|legalEntityId=2345|currency=EUR' || [ TRADE_INFO: 'id=2|type=Converts|legalEntityId=2345|currency=EUR', RESULT: 'SUCCESS', ERROR_MESSAGE: null]
        'id=3|type=Options|legalEntityId=BigBank123|currency' || [ TRADE_INFO: 'id=3|type=Options|legalEntityId=BigBank123|currency', RESULT: 'FAILURE']
    }

    def cleanup() {
        sql.call('delete from TRADES')
        sql.call('delete from TRADES_PROCESSING')
    }

}

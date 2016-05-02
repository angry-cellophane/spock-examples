package org.ka.test.integration

import groovy.sql.Sql
import org.junit.Before
import org.ka.config.TradeProcessorConfiguration
import org.ka.test.config.EmbeddedDatabaseConfiguration
import org.ka.trades.service.TradeProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [EmbeddedDatabaseConfiguration.class, TradeProcessorConfiguration.class])
class TradeServiceIntegrationTest extends Specification {

    @Autowired
    TradeProcessingService tradeService

    @Autowired
    Sql sql

    @Unroll
    def 'process tradeInfo successfully: test for #tradeInfo'() {
        when:
        tradeService.process(tradeInfo)
        then:
        def actual = sql.firstRow("select trade_info, result, error_message from TRADES_PROCESSING where trade_info = ?", tradeInfo)
        and:
        expect == actual
        where:
        tradeInfo                                        | result    | errorMessage
        'id=1|type=Buy|legalEntityId=1234'               | 'SUCCESS' | null
        'id=2|type=Sell|legalEntityId=2345|currency=EUR' | 'SUCCESS' | null

        expect = [TRADE_INFO: tradeInfo, RESULT: result, ERROR_MESSAGE: errorMessage]
    }

    @Unroll
    def 'tradeInfo processing fails with error message: test for #tradeInfo'() {
        when:
        tradeService.process(tradeInfo)
        then:
        def actual = sql.firstRow("select trade_info, result, error_message from TRADES_PROCESSING where trade_info = ?", tradeInfo)
        and:
        expect == actual
        where:
        tradeInfo                                          | result    | errorMessage
        'id=3|type=Sell|legalEntityId=BigBank123|currency' | 'FAILURE' | 'java.lang.ArrayIndexOutOfBoundsException: 1'
        'id=4|legalEntityId=SmallBank456'                  | 'FAILURE' | 'java.lang.RuntimeException: type is empty in id=4|legalEntityId=SmallBank456'

        expect = [TRADE_INFO: tradeInfo, RESULT: result, ERROR_MESSAGE: errorMessage]
    }

    def cleanup() {
        sql.call('delete from TRADES')
        sql.call('delete from TRADES_PROCESSING')
    }
}

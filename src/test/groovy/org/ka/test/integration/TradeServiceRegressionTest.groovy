package org.ka.test.integration

import groovy.sql.Sql
import org.ka.config.TradeProcessorConfiguration
import org.ka.test.config.EmbeddedDatabaseConfiguration
import org.ka.test.config.RegressionTestDatabaseConfiguration
import org.ka.trades.dao.TradeDao
import org.ka.trades.dao.TradeProcessingDao
import org.ka.trades.parser.PipeSeparatedValuesParser
import org.ka.trades.service.ParseAndSaveTradesToDatabase
import org.ka.trades.service.TradeProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification


@ContextConfiguration(classes = [RegressionTestDatabaseConfiguration.class])
class TradeServiceRegressionTest extends Specification {

    @Autowired
    Sql sql

    def 'do regression test'() {
        given:
        def parser = new PipeSeparatedValuesParser()
        def tradeDao = Mock(TradeDao)
        def tradeProcessingDao = Mock(TradeProcessingDao)
        def service = new ParseAndSaveTradesToDatabase(parser, tradeDao, tradeProcessingDao)
        when:
        service.process(message)
        then:
        noExceptionThrown()
        where:
        message << sql.rows("select * from ").collect { it['TRADE_INFO'] }
    }


}

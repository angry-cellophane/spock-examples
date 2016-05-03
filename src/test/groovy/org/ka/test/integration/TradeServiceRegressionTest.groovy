package org.ka.test.integration

import groovy.sql.Sql
import org.ka.test.config.RegressionTestDatabaseConfiguration
import org.ka.trades.dao.TradeDao
import org.ka.trades.dao.TradeProcessingDao
import org.ka.trades.parser.PipeSeparatedValuesParser
import org.ka.trades.service.ParseAndSaveTradesToDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Shared
import spock.lang.Specification

class TradeServiceRegressionTest extends Specification {

    @Shared Sql sql

    def 'do regression test for successful messages'() {
        given:
        def parser = new PipeSeparatedValuesParser()
        def tradeDao = Mock(TradeDao)
        def tradeProcessingDao = Mock(TradeProcessingDao)
        def service = new ParseAndSaveTradesToDatabase(parser, tradeDao, tradeProcessingDao)
        when:
        service.process(message)
        then:
        1 * tradeDao.save(_)
        and:
        1 * tradeProcessingDao.success(message)
        where:
        [message, result, errorMessage] << sql.rows("select trade_info, result, error_message from TRADES_PROCESSING where result = 'SUCCESS'")
    }

    def 'do regression test for failed messages'() {
        given:
        def parser = new PipeSeparatedValuesParser()
        def tradeDao = Mock(TradeDao)
        def tradeProcessingDao = Mock(TradeProcessingDao)
        def service = new ParseAndSaveTradesToDatabase(parser, tradeDao, tradeProcessingDao)
        when:
        service.process(message)
        then:
        1 * tradeProcessingDao.failure(message, errorMessage)
        where:
        [message, result, errorMessage] << sql.rows("select trade_info, result, error_message from TRADES_PROCESSING where result = 'FAILURE'")
    }

    def setupSpec() {
        sql = new AnnotationConfigApplicationContext(RegressionTestDatabaseConfiguration.class).getBean(Sql.class)
    }

    def cleanupSpec() {
        sql.call('delete from TRADES')
        sql.call('delete from TRADES_PROCESSING')
    }
}

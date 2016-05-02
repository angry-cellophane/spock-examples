package org.ka.test.integration

import groovy.sql.Sql
import org.ka.trades.dao.TradeDao
import org.ka.trades.dao.TradeProcessingDao
import org.ka.trades.parser.PipeSeparatedValuesParser
import org.ka.trades.service.ParseAndSaveTradesToDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import spock.lang.Specification

class TradeServiceRegressionTest extends Specification {

    static Sql sql

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
        message << sql.rows("select * from TRADES_PROCESSING").collect { it['TRADE_INFO'] }
    }

    def setupSpec() {
        sql = new Sql(
                new EmbeddedDatabaseBuilder()
                        .setType(EmbeddedDatabaseType.H2)
                        .addScript("db/sql/reg-test-init.sql")
                        .build()
        )
    }

    def cleanupSpec() {
        sql.call('delete from TRADES')
        sql.call('delete from TRADES_PROCESSING')
    }
}

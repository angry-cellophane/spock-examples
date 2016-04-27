package org.ka.test.integration

import groovy.sql.Sql
import org.ka.config.TradeProcessorConfiguration
import org.ka.test.config.EmbeddedDatabaseConfiguration
import org.ka.trades.service.TradeProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

@ContextConfiguration(classes = [EmbeddedDatabaseConfiguration.class, TradeProcessorConfiguration.class])
class SpockIntegrationTest extends Specification {

    @Shared @Autowired TradeProcessingService tradeService
    @Shared Sql sql

    def setupSpec() {
        sql = Sql.newInstance([url:'jdbc:hsqldb:mem:testDB', user:'sa', password:'', driver:'org.hsqldb.jdbc.JDBCDriver'])
    }

    def 'process one correct tradeInfo entry'() {
        given:
        def tradeInfo = 'id=1|type=Bond|legalEntityId=1234'
        when:
        tradeService.process(tradeInfo)
        then:
        println sql.firstRow('select * from TRADES_PROCESSING where trade_info = ?', tradeInfo)
    }

}

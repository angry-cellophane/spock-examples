package org.ka.config;

import org.ka.trades.dao.TradeDao;
import org.ka.trades.dao.TradeDaoImpl;
import org.ka.trades.dao.TradeProcessingDao;
import org.ka.trades.dao.TradeProcessingDaoImpl;
import org.ka.trades.parser.PipeSeparatedValuesParser;
import org.ka.trades.parser.TradesParser;
import org.ka.trades.service.ParseAndSaveTradesToDatabase;
import org.ka.trades.service.TradeProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class TradeProcessorConfiguration {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public TradeProcessingService tradeProcessingService() {
        return new ParseAndSaveTradesToDatabase(tradesParser(), tradeDao(), tradeProcessingDao());
    }

    @Bean
    public TradesParser tradesParser() {
        return new PipeSeparatedValuesParser();
    }

    @Bean
    public TradeDao tradeDao() {
        return new TradeDaoImpl(jdbcTemplate);
    }

    @Bean
    public TradeProcessingDao tradeProcessingDao() {
        return new TradeProcessingDaoImpl(jdbcTemplate);
    }

}

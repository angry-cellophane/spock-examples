package org.ka.config;

import org.ka.trades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Import(DatabaseConfiguration.class)
public class TradeProcessorConfiguration {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public TradeProcessingService tradeProcessingService() {
        return new ParseAndSaveTradesToDatabase(tradesParser(), tradeDao());
    }

    @Bean
    public TradesParser tradesParser() {
        return new PipeSeparatedValuesParser();
    }

    @Bean
    public TradeDao tradeDao() {
        return new TradeDaoImpl(jdbcTemplate);
    }

}

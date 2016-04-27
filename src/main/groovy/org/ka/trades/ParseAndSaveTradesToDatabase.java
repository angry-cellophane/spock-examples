package org.ka.trades;

import org.springframework.jdbc.core.JdbcTemplate;

public class ParseAndSaveTradesToDatabase implements TradeProcessingService {

    private final TradesParser parser;
    private final TradeDao dao;

    public ParseAndSaveTradesToDatabase(TradesParser parser, TradeDao dao) {
        this.parser = parser;
        this.dao = dao;
    }

    @Override
    public void process(String tradeInfo) {
        Trade trade = parser.parse(tradeInfo);
        dao.save(trade);
    }
}

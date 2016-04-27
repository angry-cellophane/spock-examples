package org.ka.trades;

import org.springframework.jdbc.core.JdbcTemplate;

public class ParseAndSaveTradesToDatabase implements TradeProcessingService {

    private final TradesParser parser;
    private final TradeDao tradeDao;
    private final TradeProcessingDao processingDao;

    public ParseAndSaveTradesToDatabase(TradesParser parser, TradeDao tradeDao, TradeProcessingDao tradeProcessingDao) {
        this.parser = parser;
        this.tradeDao = tradeDao;
        this.processingDao = tradeProcessingDao;
    }

    @Override
    public void process(String tradeInfo) {
        try {
            Trade trade = parser.parse(tradeInfo);
            tradeDao.save(trade);
            processingDao.success(tradeInfo);
        } catch (Exception e) {
            processingDao.failure(tradeInfo, e.getMessage());
        }
    }
}

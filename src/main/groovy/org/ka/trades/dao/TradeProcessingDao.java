package org.ka.trades.dao;

public interface TradeProcessingDao {
    void success(String tradeInfo);
    void failure(String tradeInfo, String errorMessage);
}

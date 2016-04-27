package org.ka.trades;

public interface TradeProcessingDao {
    void success(String tradeInfo);
    void failure(String tradeInfo, String errorMessage);
}

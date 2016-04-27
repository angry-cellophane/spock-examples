package org.ka.trades;

import org.ka.trades.model.Trade;

import java.util.Map;

public interface TradesFactory {
    Trade create(Map<String, String> attributes);
}

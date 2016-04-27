package org.ka.trades.parser;

import org.ka.trades.model.Trade;

public interface TradesParser {
    Trade parse(String trade);
}

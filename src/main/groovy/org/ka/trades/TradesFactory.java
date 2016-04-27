package org.ka.trades;

import java.util.Map;

public interface TradesFactory {
    Trade create(Map<String, String> attributes);
}

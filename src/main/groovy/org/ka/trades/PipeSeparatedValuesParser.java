package org.ka.trades;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PipeSeparatedValuesParser implements TradesParser {

    @Override
    public Trade parse(String trade) {
        Map<String, String> attributes = Arrays.stream(trade.split("|"))
                .map(attr -> attr.split("="))
                .collect(Collectors.toMap(x -> x[0], x -> x[1]));

        return null;
    }
}

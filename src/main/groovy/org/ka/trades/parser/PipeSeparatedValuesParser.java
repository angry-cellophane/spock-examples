package org.ka.trades.parser;

import org.ka.trades.model.Trade;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PipeSeparatedValuesParser implements TradesParser {

    private final Set<String> obligatoryAttributes;

    public PipeSeparatedValuesParser() {
        obligatoryAttributes = new HashSet<String>() {{
            add("id");
            add("type");
            add("legalEntityId");
        }};
    }

    @Override
    public Trade parse(String tradeInfo) {
        Map<String, String> attributes = Arrays.stream(tradeInfo.split("\\|"))
                .filter(s -> !s.isEmpty())
                .map(attr -> attr.split("="))
                .collect(Collectors.toMap(x -> x[0], x -> x[1]));

        validateInputData(attributes, tradeInfo);

        BeanWrapperImpl wrapper = new BeanWrapperImpl(new Trade());
        for (String attrName : obligatoryAttributes) {
            wrapper.setPropertyValue(attrName, attributes.get(attrName));
        }

        return (Trade) wrapper.getWrappedInstance();
    }

    private void validateInputData(Map<String, String> attributes, String tradeInfo) {
        for (String obligatoryAttribute : obligatoryAttributes) {
            String value = attributes.get(obligatoryAttribute);
            if (StringUtils.isEmpty(value)) {
                throw new RuntimeException(String.format("%s is empty in %s", obligatoryAttribute, tradeInfo));
            }
        }

    }
}

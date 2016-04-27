package org.ka.trades;

import org.springframework.jdbc.core.JdbcTemplate;

public class TradeDaoImpl implements TradeDao {

    private static final String SAVE_TRADE_QUERY = "insert into trades (id, type, legalEntityId) values (?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;


    public TradeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Trade trade) {
        jdbcTemplate.update(SAVE_TRADE_QUERY, trade.getId(), trade.getType(), trade.getLegalEntityId());
    }
}

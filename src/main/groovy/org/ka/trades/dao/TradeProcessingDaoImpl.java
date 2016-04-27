package org.ka.trades.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;

public class TradeProcessingDaoImpl implements TradeProcessingDao {

    private static final String SAVE_RESULT_QUERY =
            "insert into TRADES_PROCESSING (ts, trade_info, result, error_message) VALUES (?, ?, ?, ?)";

    static final String SUCCESS = "SUCCESS";
    static final String FAILURE = "FAILURE";

    private final JdbcTemplate jdbcTemplate;

    public TradeProcessingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void success(String tradeInfo) {
        System.out.println("it works");
        jdbcTemplate.update(SAVE_RESULT_QUERY, new Timestamp(System.currentTimeMillis()), tradeInfo, SUCCESS, null);
    }

    @Override
    public void failure(String tradeInfo, String errorMessage) {
        jdbcTemplate.update(SAVE_RESULT_QUERY, new Timestamp(System.currentTimeMillis()), tradeInfo, FAILURE, errorMessage);
    }
}

package org.ka.trades;

import org.springframework.jdbc.core.JdbcTemplate;

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
        jdbcTemplate.update(SAVE_RESULT_QUERY, System.currentTimeMillis(), tradeInfo, SUCCESS, null);
    }

    @Override
    public void failure(String tradeInfo, String errorMessage) {
        jdbcTemplate.update(SAVE_RESULT_QUERY, System.currentTimeMillis(), tradeInfo, FAILURE, errorMessage);
    }
}

DROP TABLE IF EXISTS  TRADES;
CREATE TABLE TRADES (
    id INT PRIMARY KEY,
    type VARCHAR(255),
    legal_entity_id VARCHAR(255)
);

DROP TABLE IF EXISTS TRADES_PROCESSING;
CREATE TABLE TRADES_PROCESSING (
    ts TIMESTAMP,
    trade_info VARCHAR(255),
    result VARCHAR(255),
    error_message VARCHAR(255)
);

INSERT INTO TRADES(id, type, legal_entity_id) VALUES (1233, 'Buy', 'Very Big bank 1233');
INSERT INTO TRADES(id, type, legal_entity_id) VALUES (1234, 'Buy', 'Very Big bank 1234');
INSERT INTO TRADES(id, type, legal_entity_id) VALUES (1235, 'Sell', 'Very Big bank 1235');
INSERT INTO TRADES(id, type, legal_entity_id) VALUES (1236, 'Buy', 'Very Big bank 1236');
INSERT INTO TRADES(id, type, legal_entity_id) VALUES (1237, 'Sell', 'Very Big bank 1237');

INSERT INTO TRADES_PROCESSING(ts, trade_info, result, error_message) VALUES (CURRENT_TIMESTAMP(), 'id=1233|type=Buy|legalEntityId=Very Big bank 1233', 'SUCCESS', null);
INSERT INTO TRADES_PROCESSING(ts, trade_info, result, error_message) VALUES (CURRENT_TIMESTAMP(), 'id=1234|type=Buy|legalEntityId=Very Big bank 1234|', 'SUCCESS', null);
INSERT INTO TRADES_PROCESSING(ts, trade_info, result, error_message) VALUES (CURRENT_TIMESTAMP(), 'type=Buy|legalEntityId=Very Big bank 123456|', 'FAILURE', 'java.lang.RuntimeException: id is empty in type=Buy|legalEntityId=Very Big bank 123456|');
INSERT INTO TRADES_PROCESSING(ts, trade_info, result, error_message) VALUES (CURRENT_TIMESTAMP(), 'id=1235|type=Sell|legalEntityId=Very Big bank 1235', 'SUCCESS', null);
INSERT INTO TRADES_PROCESSING(ts, trade_info, result, error_message) VALUES (CURRENT_TIMESTAMP(), 'id=1236|type=Buy|legalEntityId=Very Big bank 1236', 'SUCCESS', null);
INSERT INTO TRADES_PROCESSING(ts, trade_info, result, error_message) VALUES (CURRENT_TIMESTAMP(), 'id=1237|type=Sell|legalEntityId=Very Big bank 1237|', 'SUCCESS', null);
INSERT INTO TRADES_PROCESSING(ts, trade_info, result, error_message) VALUES (CURRENT_TIMESTAMP(), 'id=1238|legalEntityId=Very Big bank 1238|buyer', 'FAILURE', 'java.lang.ArrayIndexOutOfBoundsException: 1');
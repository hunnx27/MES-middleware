CREATE TABLE alarm_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    equipment_id VARCHAR(20),
    alarm_message VARCHAR(500),
    alarm_level VARCHAR(20),  -- WARNING, CRITICAL
    alarm_status VARCHAR(20) DEFAULT 'ACTIVE',
    occurred_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
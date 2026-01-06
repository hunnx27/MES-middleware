-- 2. PLC 생산 로그 테이블
CREATE TABLE plc_production_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    equipment_id VARCHAR(20) NOT NULL,
    signal_type VARCHAR(50) NOT NULL,
    count INT DEFAULT 1,
    cumulative INT NOT NULL,
    is_defect BOOLEAN DEFAULT FALSE,
    cycle_time DECIMAL(6,2),
    timestamp TIMESTAMP NOT NULL,
    timestamp_ms BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_equipment (equipment_id),
    INDEX idx_timestamp (timestamp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='PLC 생산 완료 로그';
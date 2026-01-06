CREATE TABLE IF NOT EXISTS sensor_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    equipment_id VARCHAR(50) NOT NULL,
    temperature DOUBLE,
    pressure DOUBLE,
    vibration DOUBLE,
    speed INT,
    timestamp TIMESTAMP NOT NULL,
    timestamp_ms BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_equipment_id (equipment_id),
    INDEX idx_timestamp (timestamp),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

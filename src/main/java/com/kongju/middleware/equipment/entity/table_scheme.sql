CREATE TABLE IF NOT EXISTS equipment_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    equipment_id VARCHAR(50) NOT NULL,
    temperature DOUBLE,
    pressure DOUBLE,
    vibration DOUBLE,
    speed INT,
    timestamp BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_equipment_id (equipment_id),
    INDEX idx_timestamp (timestamp),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 조회 성능 향상을 위한 복합 인덱스
CREATE INDEX idx_equipment_timestamp ON equipment_data(equipment_id, timestamp DESC);
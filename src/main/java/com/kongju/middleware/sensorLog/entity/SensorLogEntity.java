package com.kongju.middleware.sensorLog.entity;

import com.kongju.middleware.sensorLog.dto.SensorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "equipment_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipment_id", nullable = false)
    private String equipmentId;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "vibration")
    private Double vibration;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp; // plc가 생성한 시간(timestamp_ms)

    @Column(name = "timestamp_ms", nullable = false)
    private Long timestampMs;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 서버에 기록된 시간

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // 생성 메서드 (팩토리 패턴) : 객체를 직접 new 하지 말고, 정해진 규칙으로 만들어라
    public static SensorLogEntity fromMqtt(SensorDto dto) {
        SensorLogEntity log = new SensorLogEntity();
        log.equipmentId = dto.getEquipmentId();
        log.timestamp = dto.getTimestamp();
        log.timestampMs = dto.getTimestampMs();
        log.temperature = dto.getTemperature();
        log.pressure = dto.getPressure();
        log.vibration = dto.getVibration();
        log.speed = dto.getSpeed();
        return log;
    }
}

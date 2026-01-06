package com.kongju.middleware.plcProductionLog.entity.entity;

import com.kongju.middleware.plcProductionLog.entity.dto.PlcSignalDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipment_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlcProductionLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "equipment_id", nullable = false)
    private String equipmentId;

    @Column(name = "signal_type", nullable = false)
    private String signalType;

    @Column(name = "count")
    private Integer count;

    @Column(name = "cumulative", nullable = false)
    private Integer cumulative;

    @Column(name = "is_defect")
    private Boolean isDefect;

    @Column(name = "cycle_time")
    private Integer cycleTime;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "timestamp_ms")
    private Long timestampMs;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // 생성 메서드 (팩토리 패턴)
    public static PlcProductionLogEntity fromMqtt(PlcSignalDto dto) {
        PlcProductionLogEntity log = new PlcProductionLogEntity();
        log.equipmentId = dto.getEquipmentId();
        log.signalType = dto.getSignalType();
        log.count = dto.getCount();
        log.cumulative = dto.getCumulative();
        log.isDefect = dto.isDefect();
        log.cycleTime = dto.getCycleTime();
        log.timestamp = dto.getTimestamp();
        log.timestampMs = dto.getTimestampMs();
        return log;
    }

}

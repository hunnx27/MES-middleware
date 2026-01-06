package com.kongju.middleware.alarmLog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "alarm_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "equipment_id", nullable = false)
    private String equipmentId;

    @Column(name = "alarm_type", nullable = false)
    private String alarmType;

    @Column(name = "alarm_message", nullable = false)
    private String alarmMessage;

    @Column(name = "alarm_level", nullable = false)
    private String alarmLevel;

    @Column(name = "alarm_status")
    private String alarmStatus;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (alarmStatus == null) {
            alarmStatus = "ACTIVE";
        }
    }
}

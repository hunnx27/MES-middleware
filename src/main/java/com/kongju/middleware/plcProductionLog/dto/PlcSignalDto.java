package com.kongju.middleware.plcProductionLog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlcSignalDto {
    @JsonProperty("equipmentId")
    private String equipmentId;
    @JsonProperty("signal_type")
    private String signalType;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("cumulative")
    private Integer cumulative;
    @JsonProperty("is_defect")
    private boolean defect;
    @JsonProperty("cycle_time")
    private Integer cycleTime;
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    @JsonProperty("timestamp_ms")
    private Long timestampMs;
}

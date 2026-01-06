package com.kongju.middleware.alarmLog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {

    @JsonProperty("equipment_id")
    private String equipmentId;

    @JsonProperty("temperature")
    private Double temperature;

    @JsonProperty("pressure")
    private Double pressure;

    @JsonProperty("vibration")
    private Double vibration;

    @JsonProperty("speed")
    private Integer speed;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

}

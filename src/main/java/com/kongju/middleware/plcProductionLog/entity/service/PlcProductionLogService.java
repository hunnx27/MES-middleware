package com.kongju.middleware.plcProductionLog.entity.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kongju.middleware.plcProductionLog.entity.dto.PlcSignalDto;
import com.kongju.middleware.plcProductionLog.entity.entity.PlcProductionLogEntity;
import com.kongju.middleware.plcProductionLog.entity.repository.PlcProductionLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlcProductionLogService {

    private final PlcProductionLogRepository repository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void processAndSaveData(String jsonMessage) {
        try {
            // JSON 파싱
            PlcSignalDto dto = objectMapper.readValue(jsonMessage, PlcSignalDto.class);

            log.info("Received data: equipmentId={}, signalType={}, count={}, cumulative={}, defect={}, cycleTime={}, timestamp={}, timestampMs={}",
                    dto.getEquipmentId(), dto.getSignalType(), dto.getCount(),
                    dto.getCumulative(), dto.isDefect(), dto.getCycleTime(), dto.getTimestamp(), dto.getTimestampMs());

            // 데이터 검증 (선택사항)
            validateData(dto);

            // Entity 변환
            PlcProductionLogEntity entity = PlcProductionLogEntity.fromMqtt(dto);

            // DB 저장
            PlcProductionLogEntity saved = repository.save(entity);
            log.info("Data saved successfully with ID: {}", saved.getId());

        } catch (Exception e) {
            log.error("Error processing MQTT message: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process MQTT message", e);
        }
    }

    private void validateData(PlcSignalDto dto) {
        if (dto.getEquipmentId() == null || dto.getEquipmentId().trim().isEmpty()) {
            throw new IllegalArgumentException("Equipment ID is required");
        }
        if (dto.getTimestamp() == null) {
            throw new IllegalArgumentException("Timestamp is required");
        }
    }
}
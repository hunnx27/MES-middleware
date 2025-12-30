package com.kongju.middleware.equipment.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kongju.middleware.equipment.dto.EquipmentDataDto;
import com.kongju.middleware.equipment.entity.EquipmentDataEntity;
import com.kongju.middleware.equipment.repository.EquipmentDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentService {

    private final EquipmentDataRepository repository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void processAndSaveData(String jsonMessage) {
        try {
            // JSON 파싱
            EquipmentDataDto dto = objectMapper.readValue(jsonMessage, EquipmentDataDto.class);

            log.info("Received data: equipmentId={}, temperature={}, pressure={}, vibration={}, speed={}, timestamp={}",
                    dto.getEquipmentId(), dto.getTemperature(), dto.getPressure(),
                    dto.getVibration(), dto.getSpeed(), dto.getTimestamp());

            // 데이터 검증 (선택사항)
            validateData(dto);

            // Entity 변환
            EquipmentDataEntity entity = EquipmentDataEntity.builder()
                    .equipmentId(dto.getEquipmentId())
                    .temperature(dto.getTemperature())
                    .pressure(dto.getPressure())
                    .vibration(dto.getVibration())
                    .speed(dto.getSpeed())
                    .timestamp(dto.getTimestamp())
                    .build();

            // DB 저장
            EquipmentDataEntity saved = repository.save(entity);
            log.info("Data saved successfully with ID: {}", saved.getId());

        } catch (Exception e) {
            log.error("Error processing MQTT message: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process MQTT message", e);
        }
    }

    private void validateData(EquipmentDataDto dto) {
        if (dto.getEquipmentId() == null || dto.getEquipmentId().trim().isEmpty()) {
            throw new IllegalArgumentException("Equipment ID is required");
        }
        if (dto.getTimestamp() == null) {
            throw new IllegalArgumentException("Timestamp is required");
        }
        // 추가 검증 로직
        if (dto.getTemperature() != null && (dto.getTemperature() < -100 || dto.getTemperature() > 200)) {
            log.warn("Temperature value out of expected range: {}", dto.getTemperature());
        }
    }
}
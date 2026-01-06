package com.kongju.middleware.sensorLog.repository;

import com.kongju.middleware.sensorLog.entity.SensorLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorLogRepository extends JpaRepository<SensorLogEntity, Long> {

}

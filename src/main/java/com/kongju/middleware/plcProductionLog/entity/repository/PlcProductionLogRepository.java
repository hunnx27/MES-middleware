package com.kongju.middleware.plcProductionLog.entity.repository;

import com.kongju.middleware.plcProductionLog.entity.entity.PlcProductionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlcProductionLogRepository extends JpaRepository<PlcProductionLogEntity, Long> {

}

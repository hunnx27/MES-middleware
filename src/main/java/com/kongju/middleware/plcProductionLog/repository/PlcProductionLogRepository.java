package com.kongju.middleware.plcProductionLog.repository;

import com.kongju.middleware.plcProductionLog.entity.PlcProductionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlcProductionLogRepository extends JpaRepository<PlcProductionLogEntity, Long> {

}

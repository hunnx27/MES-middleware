package com.kongju.middleware.alarmLog.repository;

import com.kongju.middleware.alarmLog.entity.AlarmLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmLogRepository extends JpaRepository<AlarmLogEntity, Long> {

}

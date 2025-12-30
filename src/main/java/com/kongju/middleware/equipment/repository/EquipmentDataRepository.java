package com.kongju.middleware.equipment.repository;

import com.kongju.middleware.equipment.entity.EquipmentDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentDataRepository extends JpaRepository<EquipmentDataEntity, Long> {

    List<EquipmentDataEntity> findByEquipmentId(String equipmentId);

    List<EquipmentDataEntity> findByEquipmentIdOrderByTimestampDesc(String equipmentId);
}

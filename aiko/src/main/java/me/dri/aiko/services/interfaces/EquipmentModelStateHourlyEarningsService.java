package me.dri.aiko.services.interfaces;

import me.dri.aiko.entities.dto.EquipmentModelStateHourlyEarningsDTO;

import java.util.List;

public interface EquipmentModelStateHourlyEarningsService {

    List<EquipmentModelStateHourlyEarningsDTO> findAll();
    EquipmentModelStateHourlyEarningsDTO findEquipmentModelHourlyByEquipmentModelName(String modelEquipmentName);

}

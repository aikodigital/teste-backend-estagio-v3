package me.dri.aiko.services.interfaces;

import me.dri.aiko.entities.dto.EquipmentStateResponseDTO;

import java.util.List;

public interface EquipmentStateService {

    List<EquipmentStateResponseDTO> findALl();
    EquipmentStateResponseDTO findEquipmentStateByName(String nameStateEquipment);
    EquipmentStateResponseDTO findEquipmentStateById(String nameStateEquipment);


}

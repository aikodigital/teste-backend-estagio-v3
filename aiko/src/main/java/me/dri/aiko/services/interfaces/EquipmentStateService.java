package me.dri.aiko.services.interfaces;

import me.dri.aiko.entities.dto.EquipmentStateInputDTO;
import me.dri.aiko.entities.dto.EquipmentStateResponseDTO;

import java.util.List;

public interface EquipmentStateService {

    List<EquipmentStateResponseDTO> findALl();
    EquipmentStateResponseDTO findEquipmentStateByName(String nameStateEquipment);
    EquipmentStateResponseDTO findEquipmentStateById(String idStateEquipment);

    EquipmentStateResponseDTO createEquipmentState(EquipmentStateInputDTO equipmentStateInputDTO);
    EquipmentStateResponseDTO updateEquipmentStateByName(String nameStateEquipment);
    EquipmentStateResponseDTO updateEquipmentStateById(String idStateEquipment);

    void deleteEquipmentStateByName(String nameStateEquipment);
    void deleteEquipmentStateById(String idStateEquipment);


}

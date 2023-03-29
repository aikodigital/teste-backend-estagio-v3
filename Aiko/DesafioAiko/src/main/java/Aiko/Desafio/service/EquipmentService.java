package Aiko.Desafio.service;

import Aiko.Desafio.dto.EquipmentDTO;
import Aiko.Desafio.dto.EquipmentSaveDTO;
import Aiko.Desafio.dto.EquipmentUpdateDTO;

import java.util.List;

public interface EquipmentService {
    String addEquipment(EquipmentSaveDTO equipmentSaveDTO);

    List<EquipmentDTO> getAllEquipment();


    String updateEquipments(EquipmentUpdateDTO equipmentUpdateDTO);

    boolean deleteEquipment(int Id);
}

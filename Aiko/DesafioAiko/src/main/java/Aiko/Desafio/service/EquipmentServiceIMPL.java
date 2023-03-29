package Aiko.Desafio.service;

import Aiko.Desafio.dto.EquipmentDTO;
import Aiko.Desafio.dto.EquipmentSaveDTO;
import Aiko.Desafio.dto.EquipmentUpdateDTO;
import Aiko.Desafio.entity.Equipment;
import Aiko.Desafio.repo.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceIMPL implements EquipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Override
    public String addEquipment(EquipmentSaveDTO equipmentSaveDTO) {

        Equipment equipment = new Equipment(

                equipmentSaveDTO.getEquipmentName(),
                equipmentSaveDTO.getEquipmentState(),
                equipmentSaveDTO.getEquipmentModel(),
                equipmentSaveDTO.getEarningsPerHour(),
                equipmentSaveDTO.getHistoryPosition(),
                equipmentSaveDTO.getHistoryState()
        );

        equipmentRepo.save(equipment);
        return equipment.getEquipmentName();
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {

        List<Equipment> getEquipments = equipmentRepo.findAll();
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();

        for (Equipment a:getEquipments){

            EquipmentDTO equipmentDTO = new EquipmentDTO(
                    a.getEquipmentId(),
                    a.getEquipmentName(),
                    a.getEquipmentState(),
                    a.getEquipmentModel(),
                    a.getEarningsPerHour(),
                    a.getHistoryPosition(),
                    a.getHistoryState()
            );
            equipmentDTOList.add(equipmentDTO);
        }
        return equipmentDTOList;
    }

    @Override
    public String updateEquipments(EquipmentUpdateDTO equipmentUpdateDTO) {

        if (equipmentRepo.existsById(equipmentUpdateDTO.getEquipmentId())){

            Equipment equipment = equipmentRepo.getById(equipmentUpdateDTO.getEquipmentId());

            equipment.setEquipmentName(equipmentUpdateDTO.getEquipmentName());
            equipment.setEquipmentState(equipmentUpdateDTO.getEquipmentState());
            equipment.setEquipmentModel(equipmentUpdateDTO.getEquipmentModel());
            equipment.setEarningsPerHour(equipmentUpdateDTO.getEarningsPerHour());
            equipment.setHistoryState(equipmentUpdateDTO.getHistoryState());
            equipment.setHistoryPosition(equipmentUpdateDTO.getHistoryPosition());
            equipmentRepo.save(equipment);
        }else {
            System.out.println("ID don't exist");
        }
        return null;
    }

    @Override
    public boolean deleteEquipment(int Id) {

        if (equipmentRepo.existsById(Id)){

            equipmentRepo.deleteById(Id);
        }else {
            System.out.println("Id not found");
        }
        return true;
    }
}

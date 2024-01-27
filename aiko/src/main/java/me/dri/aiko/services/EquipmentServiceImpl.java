package me.dri.aiko.services;


import me.dri.aiko.entities.Equipment;
import me.dri.aiko.entities.EquipmentModel;
import me.dri.aiko.entities.dto.EquipmentInputDTO;
import me.dri.aiko.entities.dto.EquipmentResponseDTO;
import me.dri.aiko.entities.dto.EquipmentUpdateDTO;
import me.dri.aiko.exception.NotFoundEquipment;
import me.dri.aiko.exception.NotFoundMEquipmentModel;
import me.dri.aiko.repositories.EquipmentModelsRepository;
import me.dri.aiko.repositories.EquipmentRepository;
import me.dri.aiko.services.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository repository;
    private final EquipmentModelsRepository equipmentModelsRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository repository, EquipmentModelsRepository equipmentModelsRepository) {
        this.repository = repository;
        this.equipmentModelsRepository = equipmentModelsRepository;
    }

    @Override
    public List<Equipment> findAll() {
        return this.repository.findAll();
    }

    @Override
    public EquipmentResponseDTO findEquipmentByName(String nameEquipment) {
        Equipment equipment = this.repository.findByName(nameEquipment).orElseThrow(() -> new NotFoundEquipment("Not found equipment!!!"));
        return new EquipmentResponseDTO(equipment.getId().toString(), equipment.getName(), equipment.getModel().getName());
    }

    @Override
    public EquipmentResponseDTO findEquipmentById(String idEquipment) {
        Equipment equipment = this.repository.findById(UUID.fromString(idEquipment)).orElseThrow(() -> new NotFoundEquipment("Not found equipment!!!"));
        return new EquipmentResponseDTO(equipment.getId().toString(), equipment.getName(), equipment.getModel().getName());
    }

    @Override
    public UUID createEquipment(EquipmentInputDTO equipmentInputDTO) {
        EquipmentModel model = this.checkIfModelOfEquipmentExistAndReturn(equipmentInputDTO.modelName());
        Equipment newEquipment = new Equipment(null, model, equipmentInputDTO.equipmentName());
        return this.repository.save(newEquipment).getId();
    }

    @Override
    public void deleteEquipmentByName(String nameEquipment) {
        Equipment equipment = this.repository.findByName(nameEquipment).orElseThrow(() -> new NotFoundEquipment("Not found equipment!!!"));
        this.repository.delete(equipment);
    }

    @Override
    public void deleteEquipmentById(String idEquipment) {
        Equipment equipment = this.repository.findById(UUID.fromString(idEquipment)).orElseThrow(() -> new NotFoundEquipment("Not found equipment!!!!"));
        this.repository.delete(equipment);
    }

    @Override
    public UUID updateEquipmentByName(String nameEquipment, EquipmentUpdateDTO equipmentUpdateDTO) {
        Equipment equipment = this.repository.findByName(nameEquipment).orElseThrow(() -> new NotFoundEquipment("Not found equipment"));
        EquipmentModel equipmentModel = this.checkIfModelOfEquipmentExistAndReturn(equipmentUpdateDTO.nameModelEquipment());
        equipment.setName(equipmentUpdateDTO.nameEquipment());
        equipment.setModel(equipmentModel);
        this.repository.save(equipment);
        return this.repository.save(equipment).getId();
    }

    @Override
    public UUID updateEquipmentById(String idEquipment, EquipmentUpdateDTO equipmentUpdateDTO) {
        Equipment equipment = this.repository.findById(UUID.fromString(idEquipment)).orElseThrow(() -> new NotFoundEquipment("Not found equipment"));
        EquipmentModel equipmentModel = this.checkIfModelOfEquipmentExistAndReturn(equipmentUpdateDTO.nameModelEquipment());
        equipment.setName(equipmentUpdateDTO.nameEquipment());
        equipment.setModel(equipmentModel);
        this.repository.save(equipment);
        return this.repository.save(equipment).getId();
    }

    private EquipmentModel checkIfModelOfEquipmentExistAndReturn(String nameModelOfEquipment) {
        return this.equipmentModelsRepository.findByName(nameModelOfEquipment)
                .orElseThrow(() -> new NotFoundMEquipmentModel("The model: " + nameModelOfEquipment + " Not found!!"));
    }
}

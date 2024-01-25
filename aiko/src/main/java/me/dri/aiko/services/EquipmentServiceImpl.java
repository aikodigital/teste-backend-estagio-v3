package me.dri.aiko.services;


import me.dri.aiko.entities.Equipment;
import me.dri.aiko.entities.EquipmentModel;
import me.dri.aiko.entities.dto.EquipmentInputDTO;
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
    public UUID createEquipment(EquipmentInputDTO equipmentInputDTO) {
        EquipmentModel model = this.equipmentModelsRepository.findByName(equipmentInputDTO.modelName())
                .orElseThrow(() -> new NotFoundMEquipmentModel("The model: " + equipmentInputDTO.modelName() + " Not found!!"));
        Equipment newEquipment = new Equipment(null, model, equipmentInputDTO.equipmentName());
        return this.repository.save(newEquipment).getId();
    }
}

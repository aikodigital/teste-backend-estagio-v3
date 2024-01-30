package me.dri.aiko.services;

import me.dri.aiko.entities.EquipmentState;
import me.dri.aiko.entities.dto.EquipmentStateResponseDTO;
import me.dri.aiko.exception.NotFoundEquipmentState;
import me.dri.aiko.repositories.EquipmentStateRepository;
import me.dri.aiko.services.interfaces.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentStateServiceImpl  implements EquipmentStateService {

    private final EquipmentStateRepository stateRepository;

    @Autowired
    public EquipmentStateServiceImpl(EquipmentStateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<EquipmentStateResponseDTO> findALl() {
        return this.stateRepository.findAll().stream().map(e -> new EquipmentStateResponseDTO(e.getId().toString(), e.getName(), e.getColor())).toList();
    }

    @Override
    public EquipmentStateResponseDTO findEquipmentStateByName(String nameStateEquipment) {
        EquipmentState equipmentState = this.stateRepository.findEquipmentStateByName(nameStateEquipment).orElseThrow(() -> new NotFoundEquipmentState("Not found equipment state!!!"));
        return new EquipmentStateResponseDTO(equipmentState.getId().toString(), equipmentState.getName(), equipmentState.getColor());
    }

    @Override
    public EquipmentStateResponseDTO findEquipmentStateById(String idStateEquipment) {
        EquipmentState equipmentState = this.stateRepository.findEquipmentStateById(UUID.fromString(idStateEquipment)).orElseThrow(() -> new NotFoundEquipmentState("Not found equipment state!!!"));
        return new EquipmentStateResponseDTO(equipmentState.getId().toString(), equipmentState.getName(), equipmentState.getColor());
    }
}

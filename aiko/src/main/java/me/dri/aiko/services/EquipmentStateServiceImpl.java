package me.dri.aiko.services;

import jakarta.transaction.Transactional;
import me.dri.aiko.entities.EquipmentState;
import me.dri.aiko.entities.dto.EquipmentStateInputDTO;
import me.dri.aiko.entities.dto.EquipmentStateResponseDTO;
import me.dri.aiko.entities.dto.EquipmentStateUpdateDTO;
import me.dri.aiko.exception.INvalidFormatEquipmentState;
import me.dri.aiko.exception.NotFoundEquipmentState;
import me.dri.aiko.repositories.EquipmentStateRepository;
import me.dri.aiko.services.interfaces.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        this.checkInputsStrings(nameStateEquipment);
        EquipmentState equipmentState = this.stateRepository.findEquipmentStateByName(nameStateEquipment).orElseThrow(() -> new NotFoundEquipmentState("Not found equipment state!!!"));
        return new EquipmentStateResponseDTO(equipmentState.getId().toString(), equipmentState.getName(), equipmentState.getColor());
    }

    @Override
    public EquipmentStateResponseDTO findEquipmentStateById(String idStateEquipment) {
        this.checkInputsStrings(idStateEquipment);
        EquipmentState equipmentState = this.stateRepository.findEquipmentStateById(UUID.fromString(idStateEquipment)).orElseThrow(() -> new NotFoundEquipmentState("Not found equipment state!!!"));
        return new EquipmentStateResponseDTO(equipmentState.getId().toString(), equipmentState.getName(), equipmentState.getColor());
    }

    @Transactional
    @Override
    public EquipmentStateResponseDTO createEquipmentState(EquipmentStateInputDTO equipmentStateInputDTO) {
        EquipmentState equipmentState = this.checkIfEquipmentStateAlreadyExistAndReturn(equipmentStateInputDTO.name());
        if (equipmentState == null) {
            EquipmentState newEquipmentState = new EquipmentState(null, equipmentStateInputDTO.name(), equipmentStateInputDTO.color());
            return new EquipmentStateResponseDTO(this.stateRepository.save(newEquipmentState).getId().toString(), newEquipmentState.getName(), newEquipmentState.getColor());
        }
        return new EquipmentStateResponseDTO(equipmentState.getId().toString(), equipmentState.getName(), equipmentState.getColor());
    }

    @Transactional
    @Override
    public EquipmentStateResponseDTO updateEquipmentStateByName(String equipmentStateName, EquipmentStateUpdateDTO equipmentUpdateDTO) {
        EquipmentState equipmentState = this.stateRepository.findEquipmentStateByName(equipmentStateName).orElseThrow(() -> new NotFoundEquipmentState("Not found equipment state"));
        equipmentState.setName(equipmentUpdateDTO.name());
        equipmentState.setColor(equipmentUpdateDTO.color());
        return new EquipmentStateResponseDTO(this.stateRepository.save(equipmentState).getId().toString(), equipmentState.getName(), equipmentState.getColor());
    }
    @Transactional
    @Override
    public EquipmentStateResponseDTO updateEquipmentStateById(String equipmentStateId,EquipmentStateUpdateDTO equipmentUpdateDTO) {
        EquipmentState equipmentState = this.stateRepository.findEquipmentStateById(UUID.fromString(equipmentStateId)).orElseThrow(() -> new NotFoundEquipmentState("Not found equipment state"));
        equipmentState.setName(equipmentUpdateDTO.name());
        equipmentState.setColor(equipmentUpdateDTO.color());
        return new EquipmentStateResponseDTO(this.stateRepository.save(equipmentState).getId().toString(), equipmentState.getName(), equipmentState.getColor());
    }

    @Transactional
    @Override
    public void deleteEquipmentStateByName(String nameStateEquipment) {
        this.checkInputsStrings(nameStateEquipment);
        EquipmentState equipmentState = this.stateRepository.findEquipmentStateByName(nameStateEquipment).orElseThrow(() -> new NotFoundEquipmentState("Not found equipment state"));
        this.stateRepository.delete(equipmentState);
    }
    @Transactional
    @Override
    public void deleteEquipmentStateById(String idStateEquipment) {
        this.checkInputsStrings(idStateEquipment);
        EquipmentState equipmentState = this.stateRepository.findEquipmentStateById(UUID.fromString(idStateEquipment)).orElseThrow(() -> new NotFoundEquipmentState("Not found equipment state"));
        this.stateRepository.delete(equipmentState);

    }

    private EquipmentState checkIfEquipmentStateAlreadyExistAndReturn(String nameEquipmentState) {
        this.checkInputsStrings(nameEquipmentState);
        Optional<EquipmentState> equipmentState = this.stateRepository.findEquipmentStateByName(nameEquipmentState);
        return equipmentState.orElse(null);
    }

    private void checkInputsStrings(String input) {
        if (input.isBlank() || input.isEmpty()) {
            throw new INvalidFormatEquipmentState("Invalid format equipment state input");
        }
    }
}

package com.gpmrks.testebackendestagiov3.equipment_state.service.impl;

import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateDTO;
import com.gpmrks.testebackendestagiov3.equipment_state.dto.EquipmentStateForm;
import com.gpmrks.testebackendestagiov3.equipment_state.entity.EquipmentState;
import com.gpmrks.testebackendestagiov3.equipment_state.exception.CannotDeleteEquipmentStateException;
import com.gpmrks.testebackendestagiov3.equipment_state.exception.EquipmentStateNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment_state.repository.EquipmentStateRepository;
import com.gpmrks.testebackendestagiov3.equipment_state.service.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentStateServiceImpl implements EquipmentStateService {

    private EquipmentStateRepository equipmentStateRepository;

    @Autowired
    public EquipmentStateServiceImpl(EquipmentStateRepository equipmentStateRepository) {
        this.equipmentStateRepository = equipmentStateRepository;
    }

    @Override
    public List<EquipmentStateDTO> getAllEquipmentsStates() {
        List<EquipmentState> equipmentStateList = equipmentStateRepository.findAll();
        return equipmentStateList.stream().map(EquipmentStateDTO::new).toList();
    }

    @Override
    public EquipmentStateDTO getEquipmentStateById(UUID id) {
        EquipmentState equipmentState = returnEquipmentStateOrElseThrowStateNotFound(id);
        return new EquipmentStateDTO(equipmentState);
    }

    @Override
    public EquipmentStateDTO createEquipmentState(EquipmentStateForm equipmentStateToCreate) {
        EquipmentState equipmentState = new EquipmentState();

        equipmentState.setName(equipmentStateToCreate.getName());
        equipmentState.setColor(equipmentStateToCreate.getColor());

        EquipmentState equipmentStateSaved = equipmentStateRepository.save(equipmentState);
        return new EquipmentStateDTO(equipmentStateSaved);
    }

    @Override
    public EquipmentStateDTO updateEquipmentState(UUID id, EquipmentStateForm updatedEquipmentState) {
        EquipmentState equipmentState = returnEquipmentStateOrElseThrowStateNotFound(id);

        if (updatedEquipmentState.getName() == null) {
            equipmentState.setName(equipmentState.getName());
        } else {
            equipmentState.setName(updatedEquipmentState.getName());
        }

        if (updatedEquipmentState.getColor() == null) {
            equipmentState.setColor(equipmentState.getColor());
        } else {
            equipmentState.setColor(updatedEquipmentState.getColor());
        }

        EquipmentState equipmentStateUpdated = equipmentStateRepository.save(equipmentState);

        return new EquipmentStateDTO(equipmentStateUpdated);
    }

    @Override
    public void deleteEquipmentState(UUID id) {
        try {
            returnEquipmentStateOrElseThrowStateNotFound(id);
            equipmentStateRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new CannotDeleteEquipmentStateException(id);
        }
    }

    private EquipmentState returnEquipmentStateOrElseThrowStateNotFound(UUID id) {
        return equipmentStateRepository.findById(id).orElseThrow(() -> new EquipmentStateNotFoundException(id));
    }
}

package me.dri.aiko.services;

import me.dri.aiko.entities.EquipmentModelStateHourlyEarnings;
import me.dri.aiko.entities.dto.EquipmentModelResponseDTO;
import me.dri.aiko.entities.dto.EquipmentModelStateHourlyEarningsDTO;
import me.dri.aiko.entities.dto.EquipmentStateResponseDTO;
import me.dri.aiko.repositories.EquipmentMOdelStateHourlyRepository;
import me.dri.aiko.services.interfaces.EquipmentModelStateHourlyEarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentModelStateHourlEarningsServiceImpl implements EquipmentModelStateHourlyEarningsService {

    private final EquipmentMOdelStateHourlyRepository repository;

    @Autowired
    public EquipmentModelStateHourlEarningsServiceImpl(EquipmentMOdelStateHourlyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EquipmentModelStateHourlyEarningsDTO> findAll() {
        List<EquipmentModelStateHourlyEarnings> earningsList = this.repository.findAll();
        return earningsList.stream().map(earnings -> {
            return new EquipmentModelStateHourlyEarningsDTO(this.createEquipmentStateDtoResponse(earnings), this.createEquipmentModelDtoResponse(earnings), earnings.getValue());
        }).toList();
    }

    @Override
    public EquipmentModelStateHourlyEarningsDTO findEquipmentModelHourlyByEquipmentModelName(String idEquipmentModel) {
        return null;
    }

    private EquipmentStateResponseDTO createEquipmentStateDtoResponse(EquipmentModelStateHourlyEarnings earnings) {
        return new EquipmentStateResponseDTO(
                earnings.getId().getEquipmentState().getId().toString(),
                earnings.getId().getEquipmentState().getName(),
                earnings.getId().getEquipmentState().getColor()
        );
    }
    private EquipmentModelResponseDTO createEquipmentModelDtoResponse(EquipmentModelStateHourlyEarnings earnings) {
        return new EquipmentModelResponseDTO(
                earnings.getId().getEquipmentModel().getId().toString(),
                earnings.getId().getEquipmentModel().getName()
        );
    }

}

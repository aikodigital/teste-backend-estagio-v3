package me.dri.aiko.unittest;

import me.dri.aiko.entities.Equipment;
import me.dri.aiko.entities.EquipmentModel;
import me.dri.aiko.entities.dto.EquipmentInputDTO;
import me.dri.aiko.repositories.EquipmentModelsRepository;
import me.dri.aiko.repositories.EquipmentRepository;
import me.dri.aiko.services.EquipmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTest {

    @Mock
    EquipmentModelsRepository modelsRepository;

    @Mock
    EquipmentRepository equipmentRepository;

    @InjectMocks
    EquipmentServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateEquipment() {
        EquipmentModel mockedModel = new EquipmentModel(UUID.randomUUID(), "testModel");
        Equipment mockedEquipment = new Equipment(UUID.randomUUID(), mockedModel, "DiegoEquipment");
        when(this.modelsRepository.findByName(any())).thenReturn(Optional.of(mockedModel));
        this.service.createEquipment(new EquipmentInputDTO(mockedModel.getName(), mockedEquipment.getName()));
        verify(this.modelsRepository, times(1)).findByName(mockedModel.getName());
        verify(this.equipmentRepository, times(1)).save(mockedEquipment);
    }
}

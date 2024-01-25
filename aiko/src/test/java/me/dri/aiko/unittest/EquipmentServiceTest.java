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
        EquipmentModel mockedModel = mock(EquipmentModel.class);
        Equipment mockedEquipment = mock(Equipment.class);
        EquipmentInputDTO inputDTO = new EquipmentInputDTO("test", "Diego");
        when(this.modelsRepository.findByName("test")).thenReturn(Optional.of(mockedModel));
        when(this.equipmentRepository.save(mockedEquipment)).thenReturn(mockedEquipment);
        this.service.createEquipment(inputDTO);
        verify(this.modelsRepository, times(1)).findByName("test");
        verify(this.equipmentRepository, times(1)).save(mockedEquipment);

    }
}

package me.dri.aiko.unittest;

import me.dri.aiko.entities.Equipment;
import me.dri.aiko.entities.EquipmentModel;
import me.dri.aiko.entities.dto.EquipmentInputDTO;
import me.dri.aiko.repositories.EquipmentModelsRepository;
import me.dri.aiko.repositories.EquipmentRepository;
import me.dri.aiko.services.EquipmentServiceImpl;
import me.dri.aiko.services.interfaces.EquipmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTest {

    @Mock
    EquipmentModelsRepository modelsRepository;

    @Mock
    EquipmentRepository equipmentRepository;

    EquipmentService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.service = new EquipmentServiceImpl(equipmentRepository, modelsRepository);
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

    @Test
    public void testFindAllEquipments() {
        EquipmentModel mockedModel = new EquipmentModel(UUID.randomUUID(), "testModel");
        Equipment mockedEquipment = new Equipment(UUID.randomUUID(), mockedModel, "DiegoEquipment");
        List<Equipment>  mockedListEquipments = List.of(mockedEquipment, mockedEquipment);
        when(this.equipmentRepository.findAll()).thenReturn(mockedListEquipments);
        var result = this.service.findAll();
        verify(this.equipmentRepository, times(1)).findAll();
        assertEquals(mockedListEquipments.size(), result.size());
    }

}

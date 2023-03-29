package com.desafio.aiko.controllers;

import com.desafio.aiko.repositories.EquipmentRepository;
import com.desafio.aiko.services.EquipmentService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EquipmentControllerTest {

    @InjectMocks
    private EquipmentController equipmentController;
    @Mock
    private EquipmentService equipmentService;
    @Mock
    private EquipmentRepository equipmentRepository;




}
package com.aikodigital.teste_estagio_backend_v3_Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aikodigital.teste_estagio_backend_v3_Models.Equipment_Model_State_Hourly_Earnings;
import com.aikodigital.teste_estagio_backend_v3_Models.Equipment_Model_State_Hourly_Earnings;
import com.aikodigital.teste_estagio_backend_v3_Repositories.EquipmentModelStateHourlyEarningsRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EquipmentModelStateHourlyEarningsController {
    
    @Autowired
    EquipmentModelStateHourlyEarningsRepository equipmentModelStateHourlyEarningsRepository;
    
    @GetMapping("/equipmentModelStateHourlyEarnings")
    public List<Equipment_Model_State_Hourly_Earnings> getAllEquipmentModelStateHourlyEarnings() {
        return equipmentModelStateHourlyEarningsRepository.findAll();
    }
    
    @GetMapping("/equipmentModelStateHourlyEarnings/{id}")
    public ResponseEntity<Equipment_Model_State_Hourly_Earnings> getEquipmentModelStateHourlyEarningsById(@PathVariable(value = "id") int id) {
    	Equipment_Model_State_Hourly_Earnings Equipment_Model_State_Hourly_Earnings = equipmentModelStateHourlyEarningsRepository.findById(id);
        if(Equipment_Model_State_Hourly_Earnings != null) {
            return ResponseEntity.ok().body(Equipment_Model_State_Hourly_Earnings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/equipmentModelStateHourlyEarnings")
    public ResponseEntity<Equipment_Model_State_Hourly_Earnings> createEquipmentModelStateHourlyEarnings(@RequestBody EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings) {
    	Equipment_Model_State_Hourly_Earnings createdEquipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningsRepository.save(equipmentModelStateHourlyEarnings);
        return new ResponseEntity<>(createdEquipmentModelStateHourlyEarnings, HttpStatus.CREATED);
    }
    
}

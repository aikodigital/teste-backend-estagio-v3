package aelton.teste.teste.estagio.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//@Entity
//@Data
public class EquipmentModelStateHourlyEarnings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipment_model_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipment_state_id;
    private Long value;

}

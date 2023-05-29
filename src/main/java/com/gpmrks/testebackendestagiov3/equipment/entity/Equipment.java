package com.gpmrks.testebackendestagiov3.equipment.entity;

import com.gpmrks.testebackendestagiov3.equipment.dto.EquipmentDTO;
import com.gpmrks.testebackendestagiov3.equipment_model.entity.EquipmentModel;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_model_id")
    private EquipmentModel equipmentModel;
    @Column(name = "name")
    private String name;

    public Equipment() {
    }

    public Equipment(EquipmentModel equipmentModel, String name) {
        this.id = UUID.randomUUID();
        this.equipmentModel = equipmentModel;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UUID.randomUUID();
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", equipmentModel=" + equipmentModel +
                ", name='" + name + '\'' +
                '}';
    }

}

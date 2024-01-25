package me.dri.aiko.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "equipment")
@Data
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "equipment_model_id")
    private EquipmentModel model;

    private String name;

    public Equipment() {

    }


}

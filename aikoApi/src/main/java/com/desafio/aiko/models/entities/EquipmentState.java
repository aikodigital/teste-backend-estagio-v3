package com.desafio.aiko.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipment_state", schema = "operation")
public class EquipmentState implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "color")
    private String color;

    @OneToMany(mappedBy = "equipmentState", cascade = CascadeType.ALL)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<EquipmentStateHistory> equipmentStateHistories;

}

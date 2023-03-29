package com.desafio.aiko.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "equipment", schema = "operation")
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_model_id", referencedColumnName = "id")
    private EquipmentModel equipmentModel;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<EquipmentPositionHistory> positionHistories;

    @OneToMany(mappedBy = "equipment")
    private List<EquipmentStateHistory> stateHistory;

}

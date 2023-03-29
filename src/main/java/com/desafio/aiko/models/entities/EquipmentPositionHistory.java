package com.desafio.aiko.models.entities;


import com.desafio.aiko.models.id.EquipmentPositionHistoryId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "equipment_position_history", schema = "operation")
public class EquipmentPositionHistory implements Serializable {

    @EmbeddedId
    private EquipmentPositionHistoryId id;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lon")
    private Double lon;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Equipment equipment;
}

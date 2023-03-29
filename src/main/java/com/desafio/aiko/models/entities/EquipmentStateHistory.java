package com.desafio.aiko.models.entities;

import com.desafio.aiko.models.id.EquipmentStateHistoryId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipment_state_history", schema = "operation")
public class EquipmentStateHistory implements Serializable {

    @EmbeddedId
    private EquipmentStateHistoryId id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    @JsonIgnore
    private Timestamp date;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id", insertable = false, updatable = false)
    private Equipment equipment;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_state_id", referencedColumnName = "id", insertable = false, updatable = false)
    private EquipmentState equipmentState;


}

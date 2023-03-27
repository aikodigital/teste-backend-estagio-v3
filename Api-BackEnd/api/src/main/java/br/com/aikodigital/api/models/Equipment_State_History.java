package br.com.aikodigital.api.models;



import java.sql.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "equipment_state_history")
@Getter
@Setter
public class Equipment_State_History {
    
    @EmbeddedId
    private Equipment_State_History_ID id;

    @Column(name = "date")
    private Date date;

}

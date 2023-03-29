package machado.antonio.testebackendestagiov3.entities;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "equipment_state_history", schema = "operation")
public class EquipmentStateHistory {

	@EmbeddedId
	private EquipmentStateHistoryId id;

	@ManyToOne
	@JoinColumn(name = "equipment_id", insertable = false, updatable = false)
	private Equipment equipment;

	@ManyToOne
	@JoinColumn(name = "equipment_state_id", insertable = false, updatable = false)
	private EquipmentState equipmentState;

	@Column(nullable = false)
	private LocalDateTime date;

	// Getters and Setters:

	public EquipmentStateHistoryId getId() {
		return id;
	}

	public void setId(EquipmentStateHistoryId id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}

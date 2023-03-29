package aelton.teste.teste.estagio.entity;

import jakarta.persistence.Id;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

//@Entity
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
public class EquipmentPositionHistory {
    @Id
    private Long equipment_id;
    private LocalDate date;
    private Long lat;
    private Long lon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EquipmentPositionHistory that = (EquipmentPositionHistory) o;
        return equipment_id != null && Objects.equals(equipment_id, that.equipment_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

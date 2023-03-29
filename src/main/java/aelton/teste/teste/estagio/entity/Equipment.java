package aelton.teste.teste.estagio.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String equipmentModelId;

    private String name;

    @OneToOne
    @JoinColumn(name = "equipmen_model_id")
    private EquipmentModel equipmentModel;

    public EquipmentModel getEquipmemtModel(){
        return equipmentModel;

    }
    public void setEquipmentModel(EquipmentModel equipmentModel){
        this.equipmentModel = equipmentModel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Equipment equipment = (Equipment) o;
        return id != null && Objects.equals(id, equipment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }



}

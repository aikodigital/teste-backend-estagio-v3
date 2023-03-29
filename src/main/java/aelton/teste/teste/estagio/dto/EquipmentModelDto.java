package aelton.teste.teste.estagio.dto;

import aelton.teste.teste.estagio.entity.EquipmentModel;
import lombok.Data;

@Data
public class EquipmentModelDto {

    private Long id;
    private String name;



    public EquipmentModelDto(EquipmentModel equipmentModel) {
        this.id = equipmentModel.getId();
        this.name = equipmentModel.getName();
    }
}

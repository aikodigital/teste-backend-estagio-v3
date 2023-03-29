package aelton.teste.teste.estagio.dto;

import aelton.teste.teste.estagio.entity.Equipment;
import aelton.teste.teste.estagio.entity.EquipmentModel;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
@Data
public class EquipmentDto {

    private Long id;
    private String equipmentModelId;
    private String name;
    private EquipmentModel equipmentModel;

    public EquipmentDto(Equipment equipment) {
        this.id = equipment.getId();
        this.equipmentModelId = equipment.getEquipmentModelId();
        this.name =equipment.getName();
        this.equipmentModel = equipment.getEquipmentModel();
    }

    public static List<EquipmentDto> convert(List<Equipment> equipment){
        return equipment.stream().map(EquipmentDto::new).collect(Collectors.toList());
    }
}

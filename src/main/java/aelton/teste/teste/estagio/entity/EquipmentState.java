package aelton.teste.teste.estagio.entity;

import jakarta.persistence.Id;
//@Entity
public class EquipmentState {
    @Id
    private Long id;
    private String nome;
    private String color;
}

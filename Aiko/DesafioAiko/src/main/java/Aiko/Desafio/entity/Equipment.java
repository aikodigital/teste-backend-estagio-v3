package Aiko.Desafio.entity;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "Equipmentos")
public class Equipment {
    @Id
    @Column(name = "Id_do_Equipamento", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int equipmentId;
    @Column(name = "Nome_Equipamento", length = 50)
    private String equipmentName;
    @Column(name = "Estado_do_Equipamento", length = 50)
    private String equipmentState;
    @Column(name = "Modelo_do_Equipamento", length = 50)
    private String equipmentModel;
    @Column(name = "Ganhos_por_Hora_ou_Estado", length = 50)
    private int earningsPerHour;
    @Column(name = "Histórico_de_Estado", length = 50)
    private String historyState;
    @Column(name = "Histórico_de_Posicao", length = 50)
    private String historyPosition;

    public Equipment(int equipmentId, String equipmentName, String equipmentState, String equipmentModel, int earningsPerHour, String historyPosition, String historyState) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentState = equipmentState;
        this.equipmentModel = equipmentModel;
        this.earningsPerHour = earningsPerHour;
        this.historyState = historyState;
        this.historyPosition = historyPosition;
    }

    public Equipment(){

    }

    public Equipment(String equipmentName, String equipmentState, String equipmentModel, int earningsPerHour, String historyState, String historyPosition) {
        this.equipmentName = equipmentName;
        this.equipmentState = equipmentState;
        this.equipmentModel = equipmentModel;
        this.earningsPerHour = earningsPerHour;
        this.historyState = historyState;
        this.historyPosition = historyPosition;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(String equipmentState) {
        this.equipmentState = equipmentState;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public int getEarningsPerHour() {
        return earningsPerHour;
    }

    public void setEarningsPerHour(int earningsPerHour) {
        this.earningsPerHour = earningsPerHour;
    }

    public String getHistoryState() {
        return historyState;
    }

    public void setHistoryState(String historyState) {
        this.historyState = historyState;
    }

    public String getHistoryPosition() {
        return historyPosition;
    }

    public void setHistoryPosition(String historyPosition) {
        this.historyPosition = historyPosition;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentState='" + equipmentState + '\'' +
                ", equipmentModel='" + equipmentModel + '\'' +
                ", earningsPerHour=" + earningsPerHour +
                ", historyState='" + historyState + '\'' +
                ", historyPosition='" + historyPosition + '\'' +
                '}';
    }
}

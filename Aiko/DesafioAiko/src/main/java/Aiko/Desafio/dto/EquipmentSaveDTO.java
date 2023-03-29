package Aiko.Desafio.dto;

public class EquipmentSaveDTO {
    private String equipmentName;
    private String equipmentState;
    private String equipmentModel;
    private int earningsPerHour;
    private String historyState;
    private String historyPosition;

    public EquipmentSaveDTO(String equipmentName, String equipmentState, String equipmentModel, int earningsPerHour, String historyState, String historyPosition) {
        this.equipmentName = equipmentName;
        this.equipmentState = equipmentState;
        this.equipmentModel = equipmentModel;
        this.earningsPerHour = earningsPerHour;
        this.historyState = historyState;
        this.historyPosition = historyPosition;
    }

    public EquipmentSaveDTO() {

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
        return "EquipmentSaveDTO{" +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentState='" + equipmentState + '\'' +
                ", equipmentModel='" + equipmentModel + '\'' +
                ", earningsPerHour=" + earningsPerHour +
                ", historyState='" + historyState + '\'' +
                ", historyPosition='" + historyPosition + '\'' +
                '}';
    }
}

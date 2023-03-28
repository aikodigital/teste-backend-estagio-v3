package br.com.projeto.api.model;
import java.util.UUID;
import jakarta.persistence.*;
@Entity
@Table(name = "equipment_position_history")
public class EquipamentoPositionHistory{
    @Id
    private UUID equipment_id;

    private String date;

    private double lat;

    private double lon;

    public UUID getId() {
        return equipment_id;
    }
    public void setId(UUID equipment_id) {
        this.equipment_id = equipment_id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }  
}

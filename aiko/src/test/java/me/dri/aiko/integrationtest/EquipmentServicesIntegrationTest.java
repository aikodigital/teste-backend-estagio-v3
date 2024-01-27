package me.dri.aiko.integrationtest;

import io.restassured.RestAssured;
import me.dri.aiko.entities.dto.EquipmentInputDTO;
import me.dri.aiko.entities.dto.EquipmentUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class EquipmentServicesIntegrationTest {

    String idEquipementForTests;
    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/equipments";
    }

    @Test
    public void testCreateEquipment() {
        EquipmentInputDTO newEquipmentDto = new EquipmentInputDTO("Caminhão de carga", "Test");
        idEquipementForTests = String.valueOf(given().body(newEquipmentDto).when().post().then().statusCode(200).extract().response());
    }

    @Test
    public void testUpdateEquipmentByName() {
        EquipmentUpdateDTO updateDTO = new EquipmentUpdateDTO("TestUpdated", "Caminhão de carga");
        given().body(updateDTO).when().put("/Test").then().statusCode(200);

    }
    @Test
    public void testUpdateEquipmentById() {
        EquipmentInputDTO newEquipmentDto = new EquipmentInputDTO("Caminhão de carga", "Test");
        given().body(newEquipmentDto).when().put("/api/equipments/" + idEquipementForTests).then().statusCode(200);
    }
    @Test
    public void testDeleteEquipmentByName() {
        given().when().delete("/api/equipments/Test").then().statusCode(204);
    }

    @Test
    public void testDeleteEquipmentById() {
        given().when().delete("/api/equipments/" + idEquipementForTests).then().statusCode(204);
    }
}

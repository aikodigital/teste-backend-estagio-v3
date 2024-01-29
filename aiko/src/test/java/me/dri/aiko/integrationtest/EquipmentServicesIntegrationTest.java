package me.dri.aiko.integrationtest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.aiko.entities.dto.EquipmentInputDTO;
import me.dri.aiko.entities.dto.EquipmentUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EquipmentServicesIntegrationTest {

    String idEquipementForTests;
    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/equipments";
    }

    @Test
    public void testCreateEquipment() {
        EquipmentInputDTO newEquipmentDto = new EquipmentInputDTO("Caminhão de carga", "Test");
        idEquipementForTests =
                String.valueOf(given()
                .contentType(ContentType.JSON)
                .body(newEquipmentDto)
                .when()
                .post()
                .then()
                .statusCode(200).extract().response());
    }

    @Test
    public void testNotFoundEquipmentModel() {
        EquipmentInputDTO newEquipmentDto = new EquipmentInputDTO("", "Test");
                given()
                        .contentType(ContentType.JSON)
                        .body(newEquipmentDto)
                        .when()
                        .post()
                        .then()
                        .statusCode(404)
                        .body("details", equalTo("The model: " + newEquipmentDto.modelName() + " Not found!!"))
                        .extract().response();
    }


    @Test
    public void testFailedCreateEquipmentFormatException() {
        EquipmentInputDTO newEquipmentDto = new EquipmentInputDTO("Caminhão de carga", "");
                given()
                        .contentType(ContentType.JSON)
                        .body(newEquipmentDto)
                        .when()
                        .post()
                        .then()
                        .statusCode(400);
    }


    @Test
    public void testUpdateEquipmentByName() {
        EquipmentUpdateDTO updateDTO = new EquipmentUpdateDTO("TestUpdated", "Caminhão de carga");
        given()
                .contentType(ContentType.JSON)
                .body(updateDTO).when().put("/Test").then().statusCode(200);
    }
    @Test
    public void testUpdateEquipmentById() {
        EquipmentUpdateDTO newEquipmentDto = new EquipmentUpdateDTO("TestUpdated1", "Caminhão de carga");
        given().contentType(ContentType.JSON)
                .body(newEquipmentDto).when().put("/id/5d4868d0-0590-4fad-a42e-f46930759fe4").then().statusCode(200);
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

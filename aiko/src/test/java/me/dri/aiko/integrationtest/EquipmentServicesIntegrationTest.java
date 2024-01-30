package me.dri.aiko.integrationtest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.aiko.entities.dto.EquipmentInputDTO;
import me.dri.aiko.entities.dto.EquipmentUpdateDTO;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EquipmentServicesIntegrationTest {

    String idEquipmentTest = "8cd0b3f5-8b60-4494-b71b-832a3b770ca5"; // It's necessary created an equipment before tests to run tests in order
    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/equipments";
    }

    @Test
    @Order(1)
    public void testCreateEquipment() {
        EquipmentInputDTO newEquipmentDto = new EquipmentInputDTO("Caminhão de carga", "TestEquipment");
        given()
                .contentType(ContentType.JSON)
                .body(newEquipmentDto)
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
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
    @Order(3)
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
    @Order(4)
    public void testUpdateEquipmentByName() {
        EquipmentUpdateDTO updateDTO = new EquipmentUpdateDTO("TestUpdated", "Caminhão de carga");
        given()
                .contentType(ContentType.JSON)
                .body(updateDTO).when().put("/TestEquipment").then().statusCode(200);
    }
    @Test
    @Order(5)
    public void testUpdateEquipmentById() {
        EquipmentUpdateDTO newEquipmentDto = new EquipmentUpdateDTO("TestUpdated1", "Caminhão de carga");
        given().contentType(ContentType.JSON)
                .body(newEquipmentDto).when().put("/id/" + idEquipmentTest).then().statusCode(200);
    }
    @Test
    @Order(6)
    public void testFindEquipmentByName() {
        given().contentType(ContentType.JSON)
                .when().get("/TestUpdated1")
                .then().statusCode(200)
                .body("nameEquipment", equalTo("TestUpdated1"),
                        "modelEquipment", equalTo("Caminhão de carga"));
    }


    @Test
    @Order(7)
    public void testFindEquipmentById() {
        given().contentType(ContentType.JSON)
                .when().get("/id/" + idEquipmentTest)
                .then().statusCode(200)
                .body("nameEquipment", equalTo("TestUpdated1"),
                        "modelEquipment", equalTo("Caminhão de carga"));
    }
    @Test
    @Order(8)
    public void testDeleteEquipmentById() {
        given().when().delete("/id/" + idEquipmentTest).then().statusCode(204);
    }

   // @Test
   // @Order(8)
  //  public void testDeleteEquipmentByName() {
   //     given().when().delete("/TestUpdated1").then().statusCode(204);

}

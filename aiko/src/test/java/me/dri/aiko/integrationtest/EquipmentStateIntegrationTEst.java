package me.dri.aiko.integrationtest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.aiko.entities.dto.EquipmentStateInputDTO;
import me.dri.aiko.entities.dto.EquipmentStateUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class EquipmentStateIntegrationTEst {

        String idEquipmentStateTest; // It's necessary created an equipment state before tests to run tests in order

        @BeforeEach
        void setup() {
            RestAssured.baseURI = "http://localhost:8080/api/equipments/state";
        }

        @Test
        @Order(1)
        public void testCreateEquipmentState() {
            EquipmentStateInputDTO newEquipmentStateDto = new EquipmentStateInputDTO("TestState", "Green");
            idEquipmentStateTest = given()
                    .contentType(ContentType.JSON)
                    .body(newEquipmentStateDto)
                    .when()
                    .post()
                    .then()
                    .statusCode(201)
                    .body("id", notNullValue())
                    .extract()
                    .path("id");
        }

        @Test
        @Order(2)
        public void testFindAllEquipmentStates() {
            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get()
                    .then()
                    .statusCode(200);
            // Add more assertions based on your actual response structure
        }

        @Test
        @Order(3)
        public void testFindEquipmentStateByName() {
            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/TestState")
                    .then()
                    .statusCode(200)
                    .body("nameState", equalTo("TestState"),
                            "colorState", equalTo("Green"));
        }

        @Test
        @Order(4)
        public void testFindEquipmentStateById() {
            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/id/" + idEquipmentStateTest)
                    .then()
                    .statusCode(200)
                    .body("nameState", equalTo("TestState"),
                            "colorState", equalTo("Green"));
        }

        @Test
        @Order(5)
        public void testUpdateEquipmentStateByName() {
            EquipmentStateUpdateDTO updateDTO = new EquipmentStateUpdateDTO(idEquipmentStateTest, "UpdatedState", "Blue");
            given()
                    .contentType(ContentType.JSON)
                    .body(updateDTO)
                    .when()
                    .put()
                    .then()
                    .statusCode(201)
                    .body("id", equalTo(idEquipmentStateTest),
                            "nameState", equalTo("UpdatedState"),
                            "colorState", equalTo("Blue"));
        }

        @Test
        @Order(6)
        public void testUpdateEquipmentStateById() {
            EquipmentStateUpdateDTO updateDTO = new EquipmentStateUpdateDTO(idEquipmentStateTest, "UpdatedStateById", "Red");
            given()
                    .contentType(ContentType.JSON)
                    .body(updateDTO)
                    .when()
                    .put("/id")
                    .then()
                    .statusCode(201)
                    .body("id", equalTo(idEquipmentStateTest),
                            "nameState", equalTo("UpdatedStateById"),
                            "colorState", equalTo("Red"));
        }

        @Test
        @Order(7)
        public void testDeleteEquipmentStateByName() {
            given()
                    .when()
                    .delete("/TestState")
                    .then()
                    .statusCode(204);
        }

        @Test
        @Order(8)
        public void testDeleteEquipmentStateById() {
            given()
                    .when()
                    .delete("/id/" + idEquipmentStateTest)
                    .then()
                    .statusCode(204);
        }

    @Test
    @Order(9)
    public void testCreateInvalidEquipmentState() {
        // Test creating an equipment state with invalid data (e.g., empty name or color)
        EquipmentStateInputDTO invalidEquipmentStateDto = new EquipmentStateInputDTO("", "InvalidColor");
        given()
                .contentType(ContentType.JSON)
                .body(invalidEquipmentStateDto)
                .when()
                .post()
                .then()
                .statusCode(400); // Assuming you return a 400 status for invalid input
    }

    @Test
    @Order(10)
    public void testUpdateNonexistentEquipmentState() {
        // Test updating an equipment state that doesn't exist
        EquipmentStateUpdateDTO updateDTO = new EquipmentStateUpdateDTO("nonexistentId", "UpdatedState", "Blue");
        given()
                .contentType(ContentType.JSON)
                .body(updateDTO)
                .when()
                .put()
                .then()
                .statusCode(404); // Assuming you return a 404 status for not found
    }

    @Test
    @Order(11)
    public void testDeleteNonexistentEquipmentStateByName() {
        // Test deleting an equipment state by name that doesn't exist
        given()
                .when()
                .delete("/NonexistentState")
                .then()
                .statusCode(204); // Assuming you return a 204 status even if the state doesn't exist
    }

    @Test
    @Order(12)
    public void testDeleteNonexistentEquipmentStateById() {
        // Test deleting an equipment state by ID that doesn't exist
        given()
                .when()
                .delete("/id/nonexistentId")
                .then()
                .statusCode(204); // Assuming you return a 204 status even if the state doesn't exist
        }
    }


package machado.antonio.testebackendestagiov3;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;
import machado.antonio.testebackendestagiov3.dtos.EquipmentStateDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentState;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EquipmentStateControllerTest {

	@LocalServerPort
	private int databasePort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = databasePort;
	}

	@Test
	void shouldNotCreateEquipmentStateWithoutName() {

		EquipmentStateDTO newEquipmentState = new EquipmentStateDTO();
		newEquipmentState.setColor("#2e82cc");

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentState)
				.post("/equipment/state").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotCreateEquipmentStateWithoutColor() {

		EquipmentStateDTO newEquipmentState = new EquipmentStateDTO();
		newEquipmentState.setName("Sendo transportado");

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentState)
				.post("/equipment/state").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditEquipmentStateWithoutId() {

		EquipmentState equipmentStateToBeEdited = new EquipmentState();
		equipmentStateToBeEdited.setName("Sendo transportado");
		equipmentStateToBeEdited.setColor("#2e82cc");

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentStateToBeEdited)
				.put("/equipment/state").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditEquipmentStateWithoutName() {

		EquipmentState equipmentStateToBeEdited = new EquipmentState();
		equipmentStateToBeEdited.setId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));
		equipmentStateToBeEdited.setColor("#2e82cc");

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentStateToBeEdited)
				.put("/equipment/state").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}
	
	@Test
	void shouldNotEditEquipmentStateWithoutColor() {

		EquipmentState equipmentStateToBeEdited = new EquipmentState();
		equipmentStateToBeEdited.setId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));
		equipmentStateToBeEdited.setName("Sendo transportado");

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentStateToBeEdited)
				.put("/equipment/state").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}
	
}

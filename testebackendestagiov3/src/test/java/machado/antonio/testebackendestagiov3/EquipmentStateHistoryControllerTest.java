package machado.antonio.testebackendestagiov3;

import java.time.LocalDateTime;
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
import machado.antonio.testebackendestagiov3.dtos.EquipmentStateHistoryDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentStateHistory;
import machado.antonio.testebackendestagiov3.entities.EquipmentStateHistoryId;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EquipmentStateHistoryControllerTest {

	@LocalServerPort
	private int databasePort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = databasePort;
	}

	@Test
	void shouldNotCreateWithoutEquipmentId() {

		EquipmentStateHistoryDTO newEquipmentState = new EquipmentStateHistoryDTO();
		newEquipmentState.setEquipmentStateId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentState)
				.post("/equipment/state-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotCreateWithoutEquipmentStateId() {

		EquipmentStateHistoryDTO newEquipmentState = new EquipmentStateHistoryDTO();
		newEquipmentState.setEquipmentId(UUID.fromString("a7c53eb1-4f5e-4eba-9764-ad205d0891f9"));

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentState)
				.post("/equipment/state-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	/**
	 * Assures that editing a {@code equipment state history} with a null id should
	 * give a {@code bad request} HTTP status response.
	 */
	@Test
	void shouldNotEditWithNullId() {

		EquipmentStateHistory equipmentStateToBeEdited = new EquipmentStateHistory();
		equipmentStateToBeEdited.setDate(LocalDateTime.now());

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentStateToBeEdited)
				.put("/equipment/state-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithoutEquipmentId() {

		EquipmentStateHistoryId id = new EquipmentStateHistoryId();
		id.setEquipmentStateId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));
		EquipmentStateHistory equipmentStateToBeEdited = new EquipmentStateHistory();
		equipmentStateToBeEdited.setId(id);
		equipmentStateToBeEdited.setDate(LocalDateTime.now());

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentStateToBeEdited)
				.put("/equipment/state-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithoutEquipmentStateId() {

		EquipmentStateHistoryId id = new EquipmentStateHistoryId();
		id.setEquipmentId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));
		EquipmentStateHistory equipmentStateToBeEdited = new EquipmentStateHistory();
		equipmentStateToBeEdited.setId(id);
		equipmentStateToBeEdited.setDate(LocalDateTime.now());

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentStateToBeEdited)
				.put("/equipment/state-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

}

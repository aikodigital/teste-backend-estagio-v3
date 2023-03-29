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
import machado.antonio.testebackendestagiov3.dtos.EquipmentPositionHistoryDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentPositionHistory;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EquipmentPositionHistoryControllerTest {

	@LocalServerPort
	private int databasePort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = databasePort;
	}

	@Test
	void shouldNotCreateWithoutEquipmentId() {

		EquipmentPositionHistoryDTO newEquipmentPosition = new EquipmentPositionHistoryDTO();
		newEquipmentPosition.setLat(-19.126535);
		newEquipmentPosition.setLon(-45.947758);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentPosition)
				.post("equipment/position-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotCreateWithoutLatitude() {

		EquipmentPositionHistoryDTO newEquipmentPosition = new EquipmentPositionHistoryDTO();
		newEquipmentPosition.setEquipmentId(UUID.fromString("c79ef1de-92f3-4edd-bd55-553056640449"));
		newEquipmentPosition.setLon(-45.947758);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentPosition)
				.post("equipment/position-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotCreateWithoutLongitude() {

		EquipmentPositionHistoryDTO newEquipmentPosition = new EquipmentPositionHistoryDTO();
		newEquipmentPosition.setEquipmentId(UUID.fromString("c79ef1de-92f3-4edd-bd55-553056640449"));
		newEquipmentPosition.setLat(-19.126535);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentPosition)
				.post("equipment/position-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithoutEquipmentId() {

		EquipmentPositionHistory equipmentPositionToBeEdited = new EquipmentPositionHistory();
		equipmentPositionToBeEdited.setLat(-19.126535);
		equipmentPositionToBeEdited.setLon(-45.947758);
		equipmentPositionToBeEdited.setDate(LocalDateTime.now());

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentPositionToBeEdited)
				.put("equipment/position-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithoutLatitude() {

		EquipmentPositionHistory equipmentPositionToBeEdited = new EquipmentPositionHistory();
		equipmentPositionToBeEdited.setEquipmentId(UUID.fromString("c79ef1de-92f3-4edd-bd55-553056640449"));
		equipmentPositionToBeEdited.setLon(-45.947758);
		equipmentPositionToBeEdited.setDate(LocalDateTime.now());

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentPositionToBeEdited)
				.put("equipment/position-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithoutLongitude() {

		EquipmentPositionHistory equipmentPositionToBeEdited = new EquipmentPositionHistory();
		equipmentPositionToBeEdited.setEquipmentId(UUID.fromString("c79ef1de-92f3-4edd-bd55-553056640449"));
		equipmentPositionToBeEdited.setLat(-19.126535);
		equipmentPositionToBeEdited.setDate(LocalDateTime.now());

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentPositionToBeEdited)
				.put("equipment/position-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithoutDate() {

		EquipmentPositionHistory equipmentPositionToBeEdited = new EquipmentPositionHistory();
		equipmentPositionToBeEdited.setEquipmentId(UUID.fromString("c79ef1de-92f3-4edd-bd55-553056640449"));
		equipmentPositionToBeEdited.setLat(-19.126535);
		equipmentPositionToBeEdited.setLon(-45.947758);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentPositionToBeEdited)
				.put("equipment/position-history").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

}

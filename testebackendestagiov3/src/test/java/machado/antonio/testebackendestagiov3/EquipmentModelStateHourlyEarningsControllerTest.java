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
import machado.antonio.testebackendestagiov3.dtos.EquipmentModelStateHourlyEarningsDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentModelStateHourlyEarnings;
import machado.antonio.testebackendestagiov3.entities.EquipmentModelStateHourlyEarningsId;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EquipmentModelStateHourlyEarningsControllerTest {

	@LocalServerPort
	private int databasePort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = databasePort;
	}

	/**
	 * Assures that creating a {@code equipment model state hourly earnings} with an
	 * existing id should give a {@code conflict} HTTP status response.
	 */
	@Test
	void shouldNotCreateWithAnExistingId() {

		EquipmentModelStateHourlyEarningsId id = new EquipmentModelStateHourlyEarningsId();
		id.setEquipmentModelId(UUID.fromString("a3540227-2f0e-4362-9517-92f41dabbfdf"));
		id.setEquipmentStateId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));
		EquipmentModelStateHourlyEarningsDTO newEquipmentModelStateHourlyEarnings = new EquipmentModelStateHourlyEarningsDTO();
		newEquipmentModelStateHourlyEarnings.setId(id);
		newEquipmentModelStateHourlyEarnings.setValue(100.0);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentModelStateHourlyEarnings)
				.post("/equipment/model-state-hourly-earnings").then().statusCode(HttpStatus.CONFLICT.value());

	}

	/**
	 * Assures that creating a {@code equipment model state hourly earnings} with a
	 * null id should give a {@code bad request} HTTP status response.
	 */
	@Test
	void shouldNotCreateWithNullId() {

		EquipmentModelStateHourlyEarningsDTO newEquipmentModelStateHourlyEarnings = new EquipmentModelStateHourlyEarningsDTO();
		newEquipmentModelStateHourlyEarnings.setValue(100.0);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentModelStateHourlyEarnings)
				.post("/equipment/model-state-hourly-earnings").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotCreateWithoutEquipmentModelId() {

		EquipmentModelStateHourlyEarningsId id = new EquipmentModelStateHourlyEarningsId();
		id.setEquipmentStateId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));
		EquipmentModelStateHourlyEarningsDTO newEquipmentModelStateHourlyEarnings = new EquipmentModelStateHourlyEarningsDTO();
		newEquipmentModelStateHourlyEarnings.setId(id);
		newEquipmentModelStateHourlyEarnings.setValue(100.0);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentModelStateHourlyEarnings)
				.post("/equipment/model-state-hourly-earnings").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotCreateWithoutEquipmentStateId() {

		EquipmentModelStateHourlyEarningsId id = new EquipmentModelStateHourlyEarningsId();
		id.setEquipmentModelId(UUID.fromString("a3540227-2f0e-4362-9517-92f41dabbfdf"));
		EquipmentModelStateHourlyEarningsDTO newEquipmentModelStateHourlyEarnings = new EquipmentModelStateHourlyEarningsDTO();
		newEquipmentModelStateHourlyEarnings.setId(id);
		newEquipmentModelStateHourlyEarnings.setValue(100.0);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentModelStateHourlyEarnings)
				.post("/equipment/model-state-hourly-earnings").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotCreatetWithoutName() {

		EquipmentModelStateHourlyEarningsId id = new EquipmentModelStateHourlyEarningsId();
		id.setEquipmentModelId(UUID.fromString("a3540227-2f0e-4362-9517-92f41dabbfdf"));
		id.setEquipmentStateId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));
		EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsToBeEdited = new EquipmentModelStateHourlyEarnings();
		equipmentModelStateHourlyEarningsToBeEdited.setId(id);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(equipmentModelStateHourlyEarningsToBeEdited).post("/equipment/model-state-hourly-earnings").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithoutEquipmentModelId() {

		EquipmentModelStateHourlyEarningsId id = new EquipmentModelStateHourlyEarningsId();
		id.setEquipmentStateId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));
		EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsToBeEdited = new EquipmentModelStateHourlyEarnings();
		equipmentModelStateHourlyEarningsToBeEdited.setId(id);
		equipmentModelStateHourlyEarningsToBeEdited.setValue(100.0);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(equipmentModelStateHourlyEarningsToBeEdited).put("/equipment/model-state-hourly-earnings").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithoutEquipmentStateId() {

		EquipmentModelStateHourlyEarningsId id = new EquipmentModelStateHourlyEarningsId();
		id.setEquipmentModelId(UUID.fromString("a3540227-2f0e-4362-9517-92f41dabbfdf"));
		EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsToBeEdited = new EquipmentModelStateHourlyEarnings();
		equipmentModelStateHourlyEarningsToBeEdited.setId(id);
		equipmentModelStateHourlyEarningsToBeEdited.setValue(100.0);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(equipmentModelStateHourlyEarningsToBeEdited).put("/equipment/model-state-hourly-earnings").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithNullId() {

		EquipmentModelStateHourlyEarningsDTO newEquipmentModelStateHourlyEarnings = new EquipmentModelStateHourlyEarningsDTO();
		newEquipmentModelStateHourlyEarnings.setValue(100.0);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentModelStateHourlyEarnings)
				.put("/equipment/model-state-hourly-earnings").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditWithoutName() {

		EquipmentModelStateHourlyEarningsId id = new EquipmentModelStateHourlyEarningsId();
		id.setEquipmentModelId(UUID.fromString("a3540227-2f0e-4362-9517-92f41dabbfdf"));
		id.setEquipmentStateId(UUID.fromString("03b2d446-e3ba-4c82-8dc2-a5611fea6e1f"));
		EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsToBeEdited = new EquipmentModelStateHourlyEarnings();
		equipmentModelStateHourlyEarningsToBeEdited.setId(id);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(equipmentModelStateHourlyEarningsToBeEdited).put("/equipment/model-state-hourly-earnings").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}

}

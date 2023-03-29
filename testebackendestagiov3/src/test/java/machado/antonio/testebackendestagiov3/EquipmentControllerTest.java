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
import machado.antonio.testebackendestagiov3.dtos.EquipmentDTO;
import machado.antonio.testebackendestagiov3.entities.Equipment;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EquipmentControllerTest {

	@LocalServerPort
	private int databasePort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = databasePort;
	}

	@Test
	void shouldNotCreateEquipmentWithoutEquipmentModelId() {

		EquipmentDTO newEquipment = new EquipmentDTO();
		newEquipment.setName("Bob");

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipment).post("/equipment").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	void shouldNotCreateEquipmentWithoutName() {

		EquipmentDTO newEquipment = new EquipmentDTO();
		newEquipment.setEquipmentModelId(UUID.fromString("a3540227-2f0e-4362-9517-92f41dabbfdf"));

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipment).post("/equipment").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	void shouldNotEditEquipmentWithoutEquipmentId() {

		Equipment equipmentToBeEdited = new Equipment();
		equipmentToBeEdited.setEquipmentModelId(UUID.fromString("a3540227-2f0e-4362-9517-92f41dabbfdf"));
		equipmentToBeEdited.setName("Bob");

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentToBeEdited).put("/equipment").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditEquipmentWithoutEquipmentModelId() {

		Equipment equipmentToBeEdited = new Equipment();
		equipmentToBeEdited.setId(UUID.fromString("1c7e9615-cc1c-4d72-8496-190fe5791c8b"));
		equipmentToBeEdited.setName("John");

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentToBeEdited).put("/equipment").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditEquipmentWithoutName() {

		Equipment equipmentToBeEdited = new Equipment();
		equipmentToBeEdited.setId(UUID.fromString("1c7e9615-cc1c-4d72-8496-190fe5791c8b"));
		equipmentToBeEdited.setEquipmentModelId(UUID.fromString("a3540227-2f0e-4362-9517-92f41dabbfdf"));

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentToBeEdited).put("/equipment").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}

}

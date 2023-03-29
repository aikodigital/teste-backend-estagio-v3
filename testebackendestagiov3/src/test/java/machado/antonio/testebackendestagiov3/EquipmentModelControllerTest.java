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
import machado.antonio.testebackendestagiov3.dtos.EquipmentModelDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentModel;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EquipmentModelControllerTest {

	@LocalServerPort
	private int databasePort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = databasePort;
	}

	@Test
	void shouldNotCreateEquipmentModelWithoutName() {

		EquipmentModelDTO newEquipmentModel = new EquipmentModelDTO();

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(newEquipmentModel)
				.post("/equipment/model").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void shouldNotEditEquipmentModelWithoutId() {

		EquipmentModel equipmentModelToBeEdited = new EquipmentModel();
		equipmentModelToBeEdited.setName("Skidder");

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentModelToBeEdited)
				.put("/equipment/model").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}
	
	@Test
	void shouldNotEditEquipmentModelWithoutName() {

		EquipmentModel equipmentModelToBeEdited = new EquipmentModel();
		equipmentModelToBeEdited.setId(UUID.fromString("a4b0c114-acd8-4151-9449-7d12ab9bf40f"));

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(equipmentModelToBeEdited)
				.put("/equipment/model").then().statusCode(HttpStatus.BAD_REQUEST.value());

	}
	
}

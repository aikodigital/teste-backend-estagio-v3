package machado.antonio.testebackendestagiov3.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import machado.antonio.testebackendestagiov3.dtos.EquipmentModelDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentModel;
import machado.antonio.testebackendestagiov3.services.EquipmentModelService;

@RestController
@RequestMapping("/equipment/model")
public class EquipmentModelController {

	EquipmentModelService equipmentModelService;

	@Autowired
	public void setEquipmentModelService(EquipmentModelService equipmentModelService) {
		this.equipmentModelService = equipmentModelService;
	}

	/**
	 * @return a list of all {@code equipment models} which are present in the
	 *         repository.
	 */
	@GetMapping
	@RequestMapping("/all")
	public ResponseEntity<List<EquipmentModel>> get() {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentModelService.getAll());
	}

	/**
	 * @param equipmentModelId the id of an {@code equipment model}.
	 * @return the requested {@code equipment model}.
	 */
	@GetMapping
	public ResponseEntity<EquipmentModel> getEquipmentModel(@RequestParam UUID equipmentModelId) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentModelService.getById(equipmentModelId));
	}

	/**
	 * @param newEquipmentModel the {@code equipment model} to be created.
	 * @return the {@code equipment model} created by this request.
	 */
	@PostMapping
	public ResponseEntity<EquipmentModel> createEquipmentModel(@RequestBody EquipmentModelDTO newEquipmentModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(equipmentModelService.create(newEquipmentModel));
	}

	/**
	 * @param equipmetModelToBeEdited the {@code equipment model} with updated
	 *                                attributes.
	 * @return the updated {@code equipment model}.
	 */
	@PutMapping
	public ResponseEntity<EquipmentModel> editEquipmentModel(@RequestBody EquipmentModel equipmetModelToBeEdited) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentModelService.update(equipmetModelToBeEdited));
	}

	/**
	 * @param the id of the {@code equipment model} to be deleted.
	 * @return a {@code no content} http status.
	 */
	@DeleteMapping
	public ResponseEntity<?> deleteEquipmentModel(@RequestParam UUID equipmentModelId) {
		equipmentModelService.delete(equipmentModelId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}

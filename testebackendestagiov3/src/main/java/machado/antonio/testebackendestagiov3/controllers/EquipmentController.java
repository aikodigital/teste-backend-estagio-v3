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

import machado.antonio.testebackendestagiov3.dtos.EquipmentDTO;
import machado.antonio.testebackendestagiov3.entities.Equipment;
import machado.antonio.testebackendestagiov3.services.EquipmentService;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

	EquipmentService equipmentService;

	@Autowired
	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	/**
	 * @return a list of all {@code equipments} which are present in the repository.
	 */
	@GetMapping
	@RequestMapping("/all")
	public ResponseEntity<List<Equipment>> getAllEquipments() {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getAll());
	}

	/**
	 * @param equipmentId the id of the requested {@code equipment}.
	 * @return the requested {@code equipment}.
	 */
	@GetMapping
	public ResponseEntity<Equipment> getEquipment(@RequestParam UUID equipmentId) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getById(equipmentId));
	}

	/**
	 * @param newEquipment the {@code equipment} to be created.
	 * @return the {@code equipment} created by this request.
	 */
	@PostMapping
	public ResponseEntity<Equipment> createEquipment(@RequestBody EquipmentDTO newEquipment) {
		return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.create(newEquipment));
	}

	/**
	 * @param equipmentToBeEdited the {@code equipment} with updated attributes.
	 * @return the updated {@code equipment}.
	 */
	@PutMapping
	public ResponseEntity<Equipment> editEquipment(@RequestBody Equipment equipmentToBeEdited) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentService.update(equipmentToBeEdited));
	}

	/**
	 * @param equipmentId the id of the {@code equipment} to be deleted.
	 * @return a {@code no content} http status.
	 */
	@DeleteMapping
	public ResponseEntity<?> deleteEquipment(@RequestParam UUID equipmentId) {
		equipmentService.delete(equipmentId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

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

import machado.antonio.testebackendestagiov3.dtos.EquipmentStateDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentState;
import machado.antonio.testebackendestagiov3.services.EquipmentStateService;

@RestController
@RequestMapping("/equipment/state")
public class EquipmentStateController {

	EquipmentStateService equipmentStateService;

	@Autowired
	public void setEquipmentStateService(EquipmentStateService equipmentStateService) {
		this.equipmentStateService = equipmentStateService;
	}

	/**
	 * @return a list of all {@code equipment states} which are present in the
	 *         repository.
	 */
	@GetMapping
	@RequestMapping("/all")
	public ResponseEntity<List<EquipmentState>> getAllEquipmentStates() {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentStateService.getAll());
	}

	/**
	 * @param equipmentStateId the id of the requested {@code equipment state}.
	 * @return the requested {@code equipment state}.
	 */
	@GetMapping
	public ResponseEntity<EquipmentState> getEquipmentState(@RequestParam UUID equipmentStateId) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentStateService.getById(equipmentStateId));
	}

	/**
	 * @param newEquipmentState the {@code equipment state} to be created.
	 * @return the {@code equipment state} created by this request.
	 */
	@PostMapping
	public ResponseEntity<EquipmentState> createEquipmentState(@RequestBody EquipmentStateDTO newEquipmentState) {
		return ResponseEntity.status(HttpStatus.CREATED).body(equipmentStateService.create(newEquipmentState));
	}

	/**
	 * @param equipmentStateToBeEdited the {@code equipment state} with updated
	 *                                 attributes.
	 * @return the updated {@code equipment state}.
	 */
	@PutMapping
	public ResponseEntity<EquipmentState> editEquipmentState(@RequestBody EquipmentState equipmentStateToBeEdited) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentStateService.update(equipmentStateToBeEdited));
	}

	/**
	 * @param equipmentStateId the id of the {@code equipment state} to be deleted.
	 * @return a {@code no content} http status.
	 */
	@DeleteMapping
	public ResponseEntity<?> deleteEquipmentState(@RequestParam UUID equipmentStateId) {
		equipmentStateService.delete(equipmentStateId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

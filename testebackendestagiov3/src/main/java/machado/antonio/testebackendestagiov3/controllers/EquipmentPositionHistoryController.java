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

import machado.antonio.testebackendestagiov3.dtos.EquipmentPositionHistoryDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentPositionHistory;
import machado.antonio.testebackendestagiov3.services.EquipmentPositionHistoryService;

@RestController
@RequestMapping("equipment/position-history")
public class EquipmentPositionHistoryController {

	EquipmentPositionHistoryService equipmentPositionHistoryService;

	@Autowired
	public void setEquipmentPositionHistoryService(EquipmentPositionHistoryService equipmentPositionHistoryService) {
		this.equipmentPositionHistoryService = equipmentPositionHistoryService;
	}

	/**
	 * @return a list of all {@code equipment position histories} which are present
	 *         in the repository.
	 */
	@GetMapping
	@RequestMapping("/all")
	public ResponseEntity<List<EquipmentPositionHistory>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentPositionHistoryService.getAll());
	}

	/**
	 * @param equipmentId the id of an {@code equipment} to find its
	 *                    {@code position history}.
	 * @return a list of the {@code equipment position history} of the requested
	 *         {@code equipment}.
	 */
	@GetMapping
	public ResponseEntity<List<EquipmentPositionHistory>> getEquipmentPositionHistory(@RequestParam UUID equipmentId) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentPositionHistoryService.getByEquipmentId(equipmentId));
	}

	/**
	 * @param equipmentId the id of an {@code equipment} to find its most recent
	 *                    {@code position}.
	 * @return the most recent {@code position} of the requested {@code equipment}.
	 */
	@GetMapping
	@RequestMapping("/most-recent")
	public ResponseEntity<EquipmentPositionHistory> getMostRecentEquipmentPosition(@RequestParam UUID equipmentId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(equipmentPositionHistoryService.getMostRecentPosition(equipmentId));
	}

	/**
	 * @return a list with the most recent positions from all {@code equipments}
	 *         which are present in the repository.
	 */
	@GetMapping
	@RequestMapping("/most-recent/all")
	public ResponseEntity<List<EquipmentPositionHistory>> getMostRecentEquipmentPositionFromAll() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(equipmentPositionHistoryService.getMostRecentPositionFromAll());
	}

	/**
	 * @param newEquipmentPosition the new {@code equipment position}.
	 * @return the {@code equipment position} added in the repository by this
	 *         request.
	 */
	@PostMapping
	public ResponseEntity<EquipmentPositionHistory> addNewEquipmentPosition(
			@RequestBody EquipmentPositionHistoryDTO newEquipmentPosition) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(equipmentPositionHistoryService.addNewEquipmentPosition(newEquipmentPosition));
	}

	/**
	 * @param equipmentPositionToBeEdited the {@code equipment position} with
	 *                                    updated attributes.
	 * @return the updated {@code equipment}.
	 */
	@PutMapping
	public ResponseEntity<EquipmentPositionHistory> editEquipmentPosition(
			@RequestBody EquipmentPositionHistory equipmentPositionToBeEdited) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(equipmentPositionHistoryService.update(equipmentPositionToBeEdited));
	}

	/**
	 * @param equipmentId the id of the {@code equipment} to be deleted from
	 *                    {@code equipment position history} repository.
	 * @return a {@code no content} http status.
	 */
	@DeleteMapping
	public ResponseEntity<?> deleteEquipmentPosition(@RequestParam UUID equipmentId) {
		equipmentPositionHistoryService.delete(equipmentId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

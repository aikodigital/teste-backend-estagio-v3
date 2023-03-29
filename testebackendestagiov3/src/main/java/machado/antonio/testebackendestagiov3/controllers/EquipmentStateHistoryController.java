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

import machado.antonio.testebackendestagiov3.dtos.EquipmentStateHistoryDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentStateHistory;
import machado.antonio.testebackendestagiov3.services.EquipmentStateHistoryService;

@RestController
@RequestMapping("/equipment/state-history")
public class EquipmentStateHistoryController {

	EquipmentStateHistoryService equipmentStateHistoryService;

	@Autowired
	public void setEquipmentStateHistoryService(EquipmentStateHistoryService equipmentStateHistoryService) {
		this.equipmentStateHistoryService = equipmentStateHistoryService;
	}

	/**
	 * @return a list of all {@code equipment state histories} which are present in
	 *         the repository.
	 */
	@GetMapping
	@RequestMapping("/all")
	public ResponseEntity<List<EquipmentStateHistory>> getAllEquipmentStateHistory() {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistoryService.getAll());
	}

	/**
	 * @param equipmentId the id of an {@code equipment} to find its
	 *                    {@code state history}.
	 * @return a list of the {@code equipment state history} of the requested
	 *         {@code equipment}.
	 */
	@GetMapping
	public ResponseEntity<List<EquipmentStateHistory>> getEquipmentStateHistory(@RequestParam UUID equipmentId) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistoryService.getByEquipmentId(equipmentId));
	}

	/**
	 * @param equipmentId the id of an {@code equipment} to find its most recent
	 *                    {@code state}.
	 * @return the most recent {@code state} of the requested {@code equipment}.
	 */
	@GetMapping
	@RequestMapping("/most-recent")
	public ResponseEntity<EquipmentStateHistory> getMostRecentEquipmentState(@RequestParam UUID equipmentId) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistoryService.getMostRecentState(equipmentId));
	}

	/**
	 * @return a list with the most recent states from all {@code equipments} which
	 *         are present in the repository.
	 */
	@GetMapping
	@RequestMapping("/most-recent/all")
	public ResponseEntity<List<EquipmentStateHistory>> getMostRecentEquipmentStateFromAll() {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistoryService.getMostRecentStateFromAll());
	}

	/**
	 * @param equipmentStateId the id of an {@code equipment state} to find all
	 *                         {@code equipments} which currently have this state.
	 * @return a list of all {@code equipments} which currently have the requested
	 *         state in the repository.
	 */
	@GetMapping
	@RequestMapping("/by-state")
	public ResponseEntity<List<EquipmentStateHistory>> getAllEquipmentsByState(@RequestParam UUID equipmentStateId) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistoryService.getAllByState(equipmentStateId));
	}

	/**
	 * @param newStateForEquipment the new current {@code state} of an equipment.
	 * @return the new {@code equipment state} added in the repository by this
	 *         request. Will be also the most recent {@code state} of the requested
	 *         {@code equipment}
	 */
	@PostMapping
	public ResponseEntity<EquipmentStateHistory> addStateForEquipment(
			@RequestBody EquipmentStateHistoryDTO newStateForEquipment) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(equipmentStateHistoryService.addStateForEquipment(newStateForEquipment));
	}

	/**
	 * @param equipmentStateToBeEdited the {@code equipment state} with the state
	 *                                 edited and the requested date.
	 * @return the edited {@code equipment state}.
	 */
	@PutMapping
	public ResponseEntity<EquipmentStateHistory> editStateForEquipment(
			@RequestBody EquipmentStateHistory equipmentStateToBeEdited) {
		return ResponseEntity.status(HttpStatus.OK).body(equipmentStateHistoryService.edit(equipmentStateToBeEdited));
	}

	/**
	 * @param equipmentId the id of the {@code equipment} to be deleted from
	 *                    {@code equipment state history} repository.
	 * @return a {@code no content} http status.
	 */
	@DeleteMapping
	public ResponseEntity<?> deleteEquipmentStateHistory(@RequestParam UUID equipmentId) {
		equipmentStateHistoryService.delete(equipmentId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

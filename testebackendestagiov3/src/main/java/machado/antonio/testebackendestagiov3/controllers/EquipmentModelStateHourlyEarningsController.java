package machado.antonio.testebackendestagiov3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import machado.antonio.testebackendestagiov3.dtos.EquipmentModelStateHourlyEarningsDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentModelStateHourlyEarnings;
import machado.antonio.testebackendestagiov3.entities.EquipmentModelStateHourlyEarningsId;
import machado.antonio.testebackendestagiov3.services.EquipmentModelStateHourlyEarningsService;

@RestController
@RequestMapping("/equipment/model-state-hourly-earnings")
public class EquipmentModelStateHourlyEarningsController {

	EquipmentModelStateHourlyEarningsService service;

	@Autowired
	public void setService(EquipmentModelStateHourlyEarningsService service) {
		this.service = service;
	}

	/**
	 * @return a list of all {@code equipment model state hourly earnings} which are
	 *         present in the repository.
	 */
	@GetMapping
	@RequestMapping("/all")
	public ResponseEntity<List<EquipmentModelStateHourlyEarnings>> getAllEquipmentModelStateHourlyEarnings() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	/**
	 * @param id the id of the requested
	 *           {@code equipment model state hourly earnings} as an
	 *           {@link EquipmentModelStateHourlyEarningsId}.
	 * 
	 * @return the requested {@code equipment model state hourly earnings}.
	 */
	@GetMapping
	public ResponseEntity<EquipmentModelStateHourlyEarnings> getEquipmentModelStateHourlyEarnings(
			@RequestBody EquipmentModelStateHourlyEarningsId id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
	}

	/**
	 * @param newEquipmentModelStateHourlyEarnings the
	 *                                             {@code equipment model state hourly earnings}
	 *                                             to be created, as an
	 *                                             {@link EquipmentModelStateHourlyEarningsDTO}.
	 * @return the {@code equipment model state hourly earnings} created by this
	 *         request.
	 */
	@PostMapping
	public ResponseEntity<EquipmentModelStateHourlyEarnings> createEquipmentModelStateHourlyEarnings(
			@RequestBody EquipmentModelStateHourlyEarningsDTO newEquipmentModelStateHourlyEarnings) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(newEquipmentModelStateHourlyEarnings));
	}

	/**
	 * @param EquipmentModelStateHourlyEarningsToBeEdited the
	 *                                                    {@code equipment model state hourly earnings}
	 *                                                    with updated attributes.
	 * @return the updated {@code equipment model state hourly earnings}.
	 */
	@PutMapping
	public ResponseEntity<EquipmentModelStateHourlyEarnings> editEquipmentModelStateHourlyEarnings(
			@RequestBody EquipmentModelStateHourlyEarnings EquipmentModelStateHourlyEarningsToBeEdited) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(EquipmentModelStateHourlyEarningsToBeEdited));
	}

	/**
	 * @param id the id of the {@code equipment model state hourly earnings} to be deleted.
	 * @return a {@code no content} http status.
	 */
	@DeleteMapping
	public ResponseEntity<EquipmentModelStateHourlyEarnings> deleteEquipmentModelStateHourlyEarnings(
			@RequestBody EquipmentModelStateHourlyEarningsId id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

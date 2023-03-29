package machado.antonio.testebackendestagiov3.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import machado.antonio.testebackendestagiov3.dtos.EquipmentDTO;
import machado.antonio.testebackendestagiov3.entities.Equipment;
import machado.antonio.testebackendestagiov3.exceptions.CannotRequestNullException;
import machado.antonio.testebackendestagiov3.repositories.EquipmentPositionHistoryRepository;
import machado.antonio.testebackendestagiov3.repositories.EquipmentRepository;

@Service
public class EquipmentService {

	EquipmentRepository repository;
	Equipment equipment;
	EquipmentModelService equipmentModelService;
	EquipmentPositionHistoryRepository equipmentPositionHistoryRepository;

	@Autowired
	public void setRepository(EquipmentRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	@Autowired
	public void setEquipmentModelService(EquipmentModelService equipmentModelService) {
		this.equipmentModelService = equipmentModelService;
	}

	@Autowired
	public void setEquipmentPositionHistoryRepository(
			EquipmentPositionHistoryRepository equipmentPositionHistoryRepository) {
		this.equipmentPositionHistoryRepository = equipmentPositionHistoryRepository;
	}

	/**
	 * @param equipmentId the id of an {@code equipment}.
	 * @return <b>true</b> if an {@code equipment} exists with the given id or
	 *         <b>false</b> if it does not exist. It checks if the id is null.
	 * @throws CannotRequestNullException if the id is null.
	 */
	public boolean checkIfExistsById(UUID equipmentId) {
		checkIfNull(equipmentId, "equipment id");
		return repository.existsById(equipmentId);
	}

	/**
	 * @param object     the object to be validated.
	 * @param objectName the name of the object, so that the exception message can
	 *                   specify which {@code attribute} or {@code field} or
	 *                   {@code element} should not be null.
	 * @throws CannotRequestNullException if the object is null.
	 */
	void checkIfNull(Object object, String objectName) {
		if (object == null) {
			throw new CannotRequestNullException(objectName);
		}
	}

	/**
	 * @return a list of all {@code equipments} found in the repository.
	 */
	public List<Equipment> getAll() {
		return repository.findAll();
	}

	/**
	 * @param equipmentId the id to find an {@code equipment} from the repository.
	 * @return the {@code equipment} requested, else throws an {@code exception}.
	 * 
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if no {@code equipment} was found with the
	 *                                    given id.
	 */
	public Equipment getById(UUID equipmentId) {
		checkIfNull(equipmentId, "equipment id");
		Optional<Equipment> equipment = repository.findById(equipmentId);
		if (equipment.isEmpty()) {
			throw new EntityNotFoundException("The equipment with the id " + equipmentId
					+ " was not found or does not exist. Please verify if the id is correct.");
		} else {
			return equipment.get();
		}
	}

	/**
	 * @param newEquipment a new {@code equipment} as an {@link EquipmentDTO}.
	 * @return the {@code equipment} created by this request, else throws an
	 *         {@code exception}.
	 * @throws CannotRequestNullException if the new {@code equipment} or its fields
	 *                                    are null.
	 * @throws EntityNotFoundException    if no {@code equipment model} was found
	 *                                    with the given id.
	 */
	public Equipment create(EquipmentDTO newEquipment) {

		/**
		 * Checks:
		 */
		checkIfNull(newEquipment, "equipment itself");
		// Equipment Service checks if the equipment model exists with the given id.
		// Then returns the equipment model found.
		boolean foundEquipmentModel = equipmentModelService.checkIfExistsById(newEquipment.getEquipmentModelId());
		if (!foundEquipmentModel) {
			throw new EntityNotFoundException("The equipment model with the id " + newEquipment.getEquipmentModelId()
					+ " was not found or does not exist. Please verify if the id is correct.");
		}
		checkIfNull(newEquipment.getName(), "equipment name");

		equipment.setId(UUID.randomUUID());
		equipment.setEquipmentModelId(newEquipment.getEquipmentModelId());
		equipment.setName(newEquipment.getName());
		return repository.save(equipment);
	}

	/**
	 * @param equipmentToBeEdited the {@code equipment} with updated attributes.
	 * @return the updated {@code equipment}, else throws an {@code exception}.
	 * 
	 * @throws CannotRequestNullException if the updated {@code equipment} or its
	 *                                    fields are null.
	 * @throws EntityNotFoundException    if no {@code equipment} or no
	 *                                    {@code equipment model} were found with
	 *                                    the given ids.
	 */
	public Equipment update(Equipment equipmentToBeEdited) {

		// Checks equipment.
		boolean foundEquipment = checkIfExistsById(equipmentToBeEdited.getId());
		if (!foundEquipment) {
			throw new EntityNotFoundException("The equipment with the id " + equipment.getId()
					+ " was not found or does not exist. Please verify if the id is correct.");
		}

		// Equipment Model Service checks if the equipment model exits with the given
		// id.
		boolean foundEquipmentModel = equipmentModelService
				.checkIfExistsById(equipmentToBeEdited.getEquipmentModelId());
		if (!foundEquipmentModel) {
			throw new EntityNotFoundException(
					"The equipment model with the id " + equipmentToBeEdited.getEquipmentModelId()
							+ " was not found or does not exist. Please verify if the id is correct.");
		}
		checkIfNull(equipmentToBeEdited.getName(), "equipment name");

		return repository.save(equipmentToBeEdited);
	}

	/**
	 * @param equipmentId the {@code equipment} id to check whether it exits by the
	 *                    given id and then deletes it from the repository.
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if no {@code equipment} was found with the
	 *                                    given id.
	 */
	public void delete(UUID equipmentId) {
		boolean foundEquipment = checkIfExistsById(equipmentId);
		if (!foundEquipment) {
			throw new EntityNotFoundException("The equipment with the id " + equipmentId
					+ " was not found or does not exist. Please verify if the id is correct.");
		} else {
			repository.deleteById(equipmentId);
		}
	}

}

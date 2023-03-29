package machado.antonio.testebackendestagiov3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import machado.antonio.testebackendestagiov3.dtos.EquipmentModelStateHourlyEarningsDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentModelStateHourlyEarnings;
import machado.antonio.testebackendestagiov3.entities.EquipmentModelStateHourlyEarningsId;
import machado.antonio.testebackendestagiov3.exceptions.CannotRequestNullException;
import machado.antonio.testebackendestagiov3.repositories.EquipmentModelStateHourlyEarningsRepository;

@Service
public class EquipmentModelStateHourlyEarningsService {

	EquipmentModelStateHourlyEarningsRepository repository;
	EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings;
	EquipmentModelService equipmentModelService;
	EquipmentStateService equipmentStateService;

	@Autowired
	public void setRepository(EquipmentModelStateHourlyEarningsRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setEquipmentModelStateHourlyEarnings(
			EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings) {
		this.equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarnings;
	}

	@Autowired
	public void setEquipmentModelService(EquipmentModelService equipmentModelService) {
		this.equipmentModelService = equipmentModelService;
	}

	@Autowired
	public void setEquipmentStateService(EquipmentStateService equipmentStateService) {
		this.equipmentStateService = equipmentStateService;
	}

	/**
	 * @param id the id of an {@code equipment model state hourly earnings} as an
	 *           {@link EquipmentModelStateHourlyEarningsId}.
	 * @return the {@code equipment model state hourly earnings} if an
	 *         {@code equipment model} and an {@code equipment state} exists with
	 *         the given id. Else throws an exception.
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if no
	 *                                    {@code equipment model state hourly earnings}
	 *                                    was found with the given id.
	 */
	public EquipmentModelStateHourlyEarnings checkIfExistsByIdThenReturnIt(EquipmentModelStateHourlyEarningsId id) {
		checkIfNull(id, "equipment model state hourly earnings id");
		checkIfNull(id.getEquipmentModelId(), "equipment model id");
		checkIfNull(id.getEquipmentStateId(), "equipment state id");

		Optional<EquipmentModelStateHourlyEarnings> requestedEquipmentModelStateHourlyEarnings = repository
				.findById(id);
		if (requestedEquipmentModelStateHourlyEarnings.isEmpty()) {
			throw new EntityNotFoundException("The equipment model state hourly earnings with the id " + id
					+ " was not found or does not exist. Please verify if the id is correct (with the correct equipment model id and with the equipment state id).");
		} else {
			return requestedEquipmentModelStateHourlyEarnings.get();
		}
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
	 * @return a list of all {@code equipment model state hourly earnings} which are
	 *         present in the repository.
	 */
	public List<EquipmentModelStateHourlyEarnings> getAll() {
		return repository.findAll();
	}

	/**
	 * @param id the id of the requested
	 *           {@code equipment model state hourly earnings} as an
	 *           {@link EquipmentModelStateHourlyEarningsId} from the repository.
	 * 
	 * @return the requested {@code equipment model state hourly earnings}, else
	 *         throws an exception.
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if no
	 *                                    {@code equipment model state hourly earnings}
	 *                                    was found with the given id.
	 */
	public EquipmentModelStateHourlyEarnings getById(EquipmentModelStateHourlyEarningsId id) {
		return checkIfExistsByIdThenReturnIt(id);
	}

	/**
	 * @param newEquipmentModelStateHourlyEarnings the
	 *                                             {@code equipment model state hourly earnings}
	 *                                             to be created, as an
	 *                                             {@link EquipmentModelStateHourlyEarningsDTO}.
	 * 
	 * @return the {@code equipment model state hourly earnings} created by this
	 *         request. If the id is not valid, throws an exception.
	 * 
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityExistsException      if the id was found in the repository,
	 *                                    thus does not need to be created. To edit
	 *                                    an existing one, there is the REST PUT
	 *                                    method.
	 * @throws EntityNotFoundException    if no {@code equipment model} or no
	 *                                    {@code equipment state} were found with
	 *                                    the given ids.
	 */
	public EquipmentModelStateHourlyEarnings create(
			EquipmentModelStateHourlyEarningsDTO newEquipmentModelStateHourlyEarnings) {
		/**
		 * Checks if null:
		 */
		checkIfNull(newEquipmentModelStateHourlyEarnings, "equipment model state hourly earnings itself");
		checkIfNull(newEquipmentModelStateHourlyEarnings.getValue(), "equipment model state hourly earnings value");
		checkIfNull(newEquipmentModelStateHourlyEarnings.getId(), "equipment model state hourly earnings id");
		checkIfNull(newEquipmentModelStateHourlyEarnings.getId().getEquipmentModelId(), "equipment model id");
		checkIfNull(newEquipmentModelStateHourlyEarnings.getId().getEquipmentStateId(), "equipment state id");

		/**
		 * Checks for ids in the repositories.
		 */
		Optional<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsNotSupposedToExist = repository
				.findById(newEquipmentModelStateHourlyEarnings.getId());
		if (equipmentModelStateHourlyEarningsNotSupposedToExist.isPresent()) {
			throw new EntityExistsException("The equipment model state hourly earnings with the id "
					+ newEquipmentModelStateHourlyEarnings.getId()
					+ " already exists. Please verify if the id is correct. To edit an existing one, use the REST PUT request.");
		}
		boolean equipmentModelExists = equipmentModelService
				.checkIfExistsById(newEquipmentModelStateHourlyEarnings.getId().getEquipmentModelId());
		if (!equipmentModelExists) {
			throw new EntityNotFoundException("The equipment model with the id "
					+ newEquipmentModelStateHourlyEarnings.getId().getEquipmentModelId()
					+ " was not found or does not exist. Please verify if the equipment model id is correct.");
		}
		boolean equipmentStateExists = equipmentStateService
				.checkIfExistsById(newEquipmentModelStateHourlyEarnings.getId().getEquipmentStateId());
		if (!equipmentStateExists) {
			throw new EntityNotFoundException("The equipment state with the id "
					+ newEquipmentModelStateHourlyEarnings.getId().getEquipmentStateId()
					+ " was not found or does not exist. Please verify if the equipment state id is correct.");
		}
		/**
		 * Creates a new equipment model state hourly earnings.
		 */
		equipmentModelStateHourlyEarnings.setId(newEquipmentModelStateHourlyEarnings.getId());
		equipmentModelStateHourlyEarnings.setValue(newEquipmentModelStateHourlyEarnings.getValue());
		return repository.save(equipmentModelStateHourlyEarnings);
	}

	/**
	 * @param equipmentModelStateHourlyEarningsToBeEdited the
	 *                                                    {@code equipment model state hourly earnings}
	 *                                                    with updated attributes.
	 * @return the updated {@code equipment model state hourly earnings}, else
	 *         throws an exception.
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityExistsException      if an equal
	 *                                    {@code equipment model state hourly earnings}
	 *                                    was found in the repository, thus does not
	 *                                    need to be edited.
	 * @throws EntityNotFoundException    if no
	 *                                    {@code equipment model state hourly earnings}
	 *                                    was found with the given id.
	 */
	public EquipmentModelStateHourlyEarnings update(
			EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsToBeEdited) {
		/**
		 * Checks:
		 */
		checkIfNull(equipmentModelStateHourlyEarningsToBeEdited, "equipment model state hourly earnings itself");
		checkIfNull(equipmentModelStateHourlyEarningsToBeEdited.getValue(),
				"equipment model state hourly earnings value");
		EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsFound = checkIfExistsByIdThenReturnIt(
				equipmentModelStateHourlyEarningsToBeEdited.getId());
		/**
		 * Checks if they are the same (This could be implemented in every service) :
		 */
		if (equipmentModelStateHourlyEarningsFound.getValue()
				.equals(equipmentModelStateHourlyEarningsToBeEdited.getValue())) {
			throw new EntityExistsException("The equipment model state hourly earnings with the id "
					+ equipmentModelStateHourlyEarningsToBeEdited.getId()
					+ " already exists with the same attributes. Please verify if the id is correct.");
		}
		/**
		 * Saves updated attributes (the updated value of equipment model state hourly
		 * earnings).
		 */
		equipmentModelStateHourlyEarningsFound.setValue(equipmentModelStateHourlyEarningsToBeEdited.getValue());
		return repository.save(equipmentModelStateHourlyEarningsFound);
	}

	/**
	 * @param id the {@code equipment model state hourly earnings} id to check
	 *           whether it exits by the given id and then deletes it from the
	 *           repository.
	 * 
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if no
	 *                                    {@code equipment model state hourly earnings}
	 *                                    was found with the given id.
	 */
	public void delete(EquipmentModelStateHourlyEarningsId id) {
		EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarningsToDelete = checkIfExistsByIdThenReturnIt(id);
		repository.delete(equipmentModelStateHourlyEarningsToDelete);
	}

}

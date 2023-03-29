package machado.antonio.testebackendestagiov3.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import machado.antonio.testebackendestagiov3.dtos.EquipmentStateDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentState;
import machado.antonio.testebackendestagiov3.exceptions.CannotRequestNullException;
import machado.antonio.testebackendestagiov3.repositories.EquipmentStateRepository;

@Service
public class EquipmentStateService {

	EquipmentStateRepository repository;
	EquipmentState equipmentState;

	@Autowired
	public void setRepository(EquipmentStateRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setEquipmentState(EquipmentState equipmentState) {
		this.equipmentState = equipmentState;
	}

	/**
	 * @param equipmentId the id of an {@code equipment state}.
	 * @return <b>true</b> if an {@code equipment state} exists with the given id or
	 *         <b>false</b> if it does not exist. It checks if the id is null.
	 * @throws CannotRequestNullException if the id is null.
	 */
	public boolean checkIfExistsById(UUID equipmentStateId) {
		checkIfNull(equipmentStateId, "equipment state id");
		return repository.existsById(equipmentStateId);
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
	 * @return a list of all {@code equipment states} which are present in the
	 *         repository.
	 */
	public List<EquipmentState> getAll() {
		return repository.findAll();
	}

	/**
	 * @param equipmentStateId the id to find an {@code equipment state} from the
	 *                         repository.
	 * @return the {@code equipment state} requested, else throws an
	 *         {@code exception}.
	 * 
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if the {@code equipment state} was not
	 *                                    found with the given id.
	 */
	public EquipmentState getById(UUID equipmentStateId) {
		checkIfNull(equipmentStateId, "equipment state id");
		Optional<EquipmentState> equipmentState = repository.findById(equipmentStateId);
		if (equipmentState.isEmpty()) {
			throw new EntityNotFoundException("The equipment state with the id " + equipmentStateId
					+ " was not found or does not exist. Please verify if the id is correct.");
		} else {
			return equipmentState.get();
		}
	}

	/**
	 * @param newEquipmentState a new {@code equipment state} as an
	 *                          {@link EquipmentStateDTO}.
	 * @return the {@code equipment state} created by this request, else throws an
	 *         {@code exception}.
	 * @throws CannotRequestNullException if the new {@code equipment state} or its
	 *                                    fields are null.
	 */
	public EquipmentState create(EquipmentStateDTO newEquipmentState) {
		checkIfNull(newEquipmentState, "equipment state itself");
		checkIfNull(newEquipmentState.getName(), "equipment state name");
		checkIfNull(newEquipmentState.getColor(), "equipment state color");

		equipmentState.setId(UUID.randomUUID());
		equipmentState.setName(newEquipmentState.getName());
		equipmentState.setColor(newEquipmentState.getColor());
		return repository.save(equipmentState);
	}

	/**
	 * @param equipmentStateToBeEdited the {@code equipment state} with updated
	 *                                 attributes.
	 * @return the updated {@code equipment state}, else throws an
	 *         {@code exception}.
	 * @throws CannotRequestNullException if the updated {@code equipment state} or
	 *                                    its fields are null.
	 * @throws EntityNotFoundException    if no {@code equipment state} was found
	 *                                    with the given id.
	 */
	public EquipmentState update(EquipmentState equipmentStateToBeEdited) {
		/**
		 * Checks:
		 */
		checkIfNull(equipmentStateToBeEdited, "equipment state itself");
		boolean foundEquipmentState = checkIfExistsById(equipmentStateToBeEdited.getId());
		if (!foundEquipmentState) {
			throw new EntityNotFoundException("The equipment state with the id " + equipmentStateToBeEdited.getId()
					+ " was not found or does not exist. Please verify if the id is correct.");
		}
		checkIfNull(equipmentStateToBeEdited.getName(), "equipment state name");
		checkIfNull(equipmentStateToBeEdited.getColor(), "equipment state color");

		return repository.save(equipmentStateToBeEdited);
	}

	/**
	 * @param equipmentStateId the {@code equipment state} id to check whether it
	 *                         exits by the given id and then deletes it from the
	 *                         repository.
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if no {@code equipment state} was found
	 *                                    with the given id.
	 */
	public void delete(UUID equipmentStateId) {
		boolean foundEquipmentState = checkIfExistsById(equipmentStateId);
		if (!foundEquipmentState) {
			throw new EntityNotFoundException("The equipment state with the id " + equipmentStateId
					+ " was not found or does not exist. Please verify if the id is correct.");
		} else {
			repository.deleteById(equipmentStateId);
		}
	}

}

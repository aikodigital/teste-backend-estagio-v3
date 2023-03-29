package machado.antonio.testebackendestagiov3.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import machado.antonio.testebackendestagiov3.dtos.EquipmentModelDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentModel;
import machado.antonio.testebackendestagiov3.exceptions.CannotRequestNullException;
import machado.antonio.testebackendestagiov3.repositories.EquipmentModelRepository;

@Service
public class EquipmentModelService {

	EquipmentModelRepository repository;
	EquipmentModel equipmentModel;

	@Autowired
	public void setRepository(EquipmentModelRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setEquipmentModel(EquipmentModel equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	/**
	 * @param equipmentModelId the id of an {@code equipment model}.
	 * @return <b>true</b> if an {@code equipment model} exists with the given id or
	 *         <b>false</b> if it does not exist. It checks if the id is null.
	 * @throws CannotRequestNullException if the id is null.
	 */
	public boolean checkIfExistsById(UUID equipmentModelId) {
		checkIfNull(equipmentModelId, "equipment model id");
		return repository.existsById(equipmentModelId);
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
	 * @return a list of all {@code equipment models} found in the repository.
	 */
	public List<EquipmentModel> getAll() {
		return repository.findAll();
	}

	/**
	 * @param equipmentModelId the id of an {@code equipment model}.
	 * @return the {@code equipment model} found by the given id, or else throws an
	 *         {@code exception}.
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if no {@code equipment model} was found
	 *                                    with the given id.
	 */
	public EquipmentModel getById(UUID equipmentModelId) {
		checkIfNull(equipmentModelId, "equipment model id");
		Optional<EquipmentModel> equipmentModel = repository.findById(equipmentModelId);
		if (equipmentModel.isEmpty()) {
			throw new EntityNotFoundException("The equipment model with the id " + equipmentModelId
					+ " was not found or does not exist. Please verify if the id is correct.");
		} else {
			return equipmentModel.get();
		}
	}

	/**
	 * @param newEquipmentModel a new {@code equipment model} as an
	 *                          {@link EquipmentModelDTO}.
	 * @return the {@code equipment model} created from this request, else throws an
	 *         {@code exception}.
	 * @throws CannotRequestNullException if the {@code equipment model} or its
	 *                                    fields are null.
	 */
	public EquipmentModel create(EquipmentModelDTO newEquipmentModel) {
		checkIfNull(newEquipmentModel, "equipment model itself");
		checkIfNull(newEquipmentModel.getName(), "equipment model name");

		equipmentModel.setId(UUID.randomUUID());
		equipmentModel.setName(newEquipmentModel.getName());
		return repository.save(equipmentModel);
	}

	/**
	 * @param equipmetModelToBeEdited the {@code equipment model} with updated
	 *                                attributes/fields.
	 * @return the updated {@code equipment model}, else throws an {@code exception}
	 * 
	 * @throws CannotRequestNullException if the {@code equipment model} or its
	 *                                    fields are null.
	 * @throws EntityNotFoundException    if no {@code equipment model} was found
	 *                                    with the given id.
	 */
	public EquipmentModel update(EquipmentModel equipmetModelToBeEdited) {

		/**
		 * Checks:
		 */
		checkIfNull(equipmetModelToBeEdited, "equipment model itself");
		boolean foundEquipmentModel = checkIfExistsById(equipmetModelToBeEdited.getId());
		if (!foundEquipmentModel) {
			throw new EntityNotFoundException("The equipment model with the id " + equipmetModelToBeEdited.getId()
					+ " was not found or does not exist. Please verify if the id is correct.");
		}
		checkIfNull(equipmetModelToBeEdited.getName(), "equipment model name");

		return repository.save(equipmetModelToBeEdited);
	}

	/**
	 * @param equipmentModelId the {@code equipment model} id to check whether it
	 *                         exits by the given id and then deletes it from the
	 *                         repository.
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if no {@code equipment model} was found
	 *                                    with the given id.
	 */
	public void delete(UUID equipmentModelId) {
		checkIfNull(equipmentModelId, "equipment id");
		boolean foundEquipmentModel = repository.existsById(equipmentModelId);
		if (!foundEquipmentModel) {
			throw new EntityNotFoundException("The equipment model with the id " + equipmentModelId
					+ " was not found or does not exist. Please verify if the id is correct.");
		} else {
			repository.deleteById(equipmentModelId);
		}
	}

}

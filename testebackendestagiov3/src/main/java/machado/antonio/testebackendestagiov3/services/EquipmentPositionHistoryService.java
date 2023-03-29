package machado.antonio.testebackendestagiov3.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import machado.antonio.testebackendestagiov3.dtos.EquipmentPositionHistoryDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentPositionHistory;
import machado.antonio.testebackendestagiov3.exceptions.CannotRequestNullException;
import machado.antonio.testebackendestagiov3.repositories.EquipmentPositionHistoryRepository;

@Service
public class EquipmentPositionHistoryService {

	EquipmentPositionHistoryRepository repository;
	EquipmentPositionHistory equipmentPositionHistory;

	@Autowired
	public void setRepository(EquipmentPositionHistoryRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setEquipmentPositionHistory(EquipmentPositionHistory equipmentPositionHistory) {
		this.equipmentPositionHistory = equipmentPositionHistory;
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
	 * @return a list of all {@code equipment position histories} found in the
	 *         repository.
	 */
	public List<EquipmentPositionHistory> getAll() {
		return repository.findAll();
	}

	/**
	 * @param equipmentId the id of an {@code equipment} to find its
	 *                    {@code position history} from the repository.
	 * @return a list of the {@code equipment position history} requested, else
	 *         throws an {@code exception}.
	 * 
	 * @throws CannotRequestNullException if the {@code equipment} id is null.
	 * @throws EntityNotFoundException    if no {@code equipment position history}
	 *                                    was found with the given {@code equipment}
	 *                                    id.
	 */
	public List<EquipmentPositionHistory> getByEquipmentId(UUID equipmentId) {
		checkIfNull(equipmentId, "equipment id");
		List<EquipmentPositionHistory> requestedEquipmentPositionHistory = repository.findAll().stream()
				.filter(equipmentPositionHistory -> equipmentPositionHistory.getEquipmentId().equals(equipmentId))
				.collect(Collectors.toList());
		if (requestedEquipmentPositionHistory.isEmpty()) {
			throw new EntityNotFoundException("The equipment with the id " + equipmentId
					+ " was not found or does not exist. Please verify if the id is correct.");
		} else {
			return requestedEquipmentPositionHistory;
		}
	}

	/**
	 * @param equipmentId the id of an {@code equipment} to find its most recent
	 *                    {@code position}.
	 * @return the most recent {@code position} of the requested {@code equipment},
	 *         else throws an exception.
	 * @throws CannotRequestNullException if the {@code equipment} id is null.
	 * @throws EntityNotFoundException    if no {@code equipment position history}
	 *                                    was found with the given {@code equipment}
	 *                                    id.
	 */
	public EquipmentPositionHistory getMostRecentPosition(UUID equipmentId) {
		checkIfNull(equipmentId, "equipment id");
		/**
		 * Finds all positions with corresponding equipment id.
		 */
		List<EquipmentPositionHistory> filteredById = repository.findAll().stream()
				.filter(equipmentPosition -> equipmentPosition.getEquipmentId().equals(equipmentId))
				.collect(Collectors.toList());
		if (filteredById.isEmpty()) {
			throw new EntityNotFoundException("The equipment with the id " + equipmentId
					+ " was not found or does not exist. Please verify if the id is correct.");
		}
		/**
		 * Finds the most recent position by date.
		 */
		LocalDateTime mostRecentPositionTime = filteredById.stream()
				.map(equipmentPosition -> equipmentPosition.getDate()).max((date1, date2) -> date1.compareTo(date2))
				.get();
		/**
		 * Finds the requested equipment with the most recent position.
		 */
		EquipmentPositionHistory equipmentWithTheMostRecentPosition = filteredById.stream()
				.filter(equipmentPosition -> equipmentPosition.getDate().equals(mostRecentPositionTime)).findAny()
				.get();
		return equipmentWithTheMostRecentPosition;
	}

	/**
	 * @return a list with the most recent positions from all {@code equipments}
	 *         which are present in the repository.
	 */
	public List<EquipmentPositionHistory> getMostRecentPositionFromAll() {
		List<EquipmentPositionHistory> listOfMostRecentPositionFromAll = repository.findAll().stream().distinct()
				.map(equipmentPosition -> getMostRecentPosition(equipmentPosition.getEquipmentId()))
				.collect(Collectors.toList());
		return listOfMostRecentPositionFromAll;
	}

	/**
	 * @param newEquipmentPosition a new {@code equipment position} as an
	 *                             {@link EquipmentPositionHistoryDTO}.
	 * @return the {@code equipment position} created by this request, else throws
	 *         an {@code exception}.
	 * @throws CannotRequestNullException if the new {@code equipment position} or
	 *                                    its fields are null.
	 * @throws EntityNotFoundException    if no {@code equipment} was found with the
	 *                                    given id.
	 */
	public EquipmentPositionHistory addNewEquipmentPosition(EquipmentPositionHistoryDTO newEquipmentPosition) {
		checkIfNull(newEquipmentPosition, "equipment position itself");
		checkIfNull(newEquipmentPosition.getEquipmentId(), "equipment id");
		checkIfNull(newEquipmentPosition.getLat(), "equipment position latitude");
		checkIfNull(newEquipmentPosition.getLon(), "equipment position longitude");

		boolean foundEquipment = repository.findAll().stream().anyMatch(
				equipmentPosition -> equipmentPosition.getEquipmentId().equals(newEquipmentPosition.getEquipmentId()));
		if (!foundEquipment) {
			throw new EntityNotFoundException("The equipment with the id " + newEquipmentPosition.getEquipmentId()
					+ " was not found or does not exist. Please verify if the id is correct.");
		}
		equipmentPositionHistory.setEquipmentId(newEquipmentPosition.getEquipmentId());
		equipmentPositionHistory.setDate(LocalDateTime.now());
		equipmentPositionHistory.setLat(newEquipmentPosition.getLat());
		equipmentPositionHistory.setLon(newEquipmentPosition.getLon());
		return repository.save(equipmentPositionHistory);
	}

	/**
	 * @param equipmentPositionToBeEdited the {@code equipment position} with
	 *                                    updated attributes.
	 * @return the updated {@code equipment position}, else throws an
	 *         {@code exception}.
	 * @throws CannotRequestNullException if the updated {@code equipment position}
	 *                                    or its fields are null.
	 * @throws EntityNotFoundException    if no {@code equipment} was found with the
	 *                                    given id or if no
	 *                                    {@code equipment position} was found with
	 *                                    the given date.
	 */
	public EquipmentPositionHistory update(EquipmentPositionHistory equipmentPositionToBeEdited) {
		checkIfNull(equipmentPositionToBeEdited, "equipment position itself");
		checkIfNull(equipmentPositionToBeEdited.getEquipmentId(), "equipment id");
		checkIfNull(equipmentPositionToBeEdited.getDate(), "equipment position date");
		checkIfNull(equipmentPositionToBeEdited.getLat(), "equipment position latitude");
		checkIfNull(equipmentPositionToBeEdited.getLon(), "equipment position longitude");

		List<EquipmentPositionHistory> listOfEquipmentPositionHistory = repository.findAll();
		boolean foundEquipmentAndRequestedDate = listOfEquipmentPositionHistory.stream()
				.filter(equipmentPosition -> equipmentPosition.getEquipmentId()
						.equals(equipmentPositionToBeEdited.getEquipmentId()))
				.anyMatch(
						equipmentPosition -> equipmentPosition.getDate().equals(equipmentPositionToBeEdited.getDate()));
		if (!foundEquipmentAndRequestedDate) {
			throw new EntityNotFoundException(
					"The equipment with the id " + equipmentPositionToBeEdited.getEquipmentId()
							+ " with the equipment position date " + equipmentPositionToBeEdited.getDate()
							+ " was not found or does not exist. Please verify if the id and the date are correct.");
		} else {
			equipmentPositionHistory.setEquipmentId(equipmentPositionToBeEdited.getEquipmentId());
			equipmentPositionHistory.setDate(equipmentPositionToBeEdited.getDate());
			equipmentPositionHistory.setLat(equipmentPositionToBeEdited.getLat());
			equipmentPositionHistory.setLon(equipmentPositionToBeEdited.getLon());
			return repository.save(equipmentPositionHistory);
		}
	}

	/**
	 * @param equipmentId the {@code equipment} id to check whether it exits by the
	 *                    given id and then deletes it from the repository. Else
	 *                    throws an exception.
	 * @throws CannotRequestNullException if the id is null.
	 * @throws EntityNotFoundException    if no {@code equipment} was found with the
	 *                                    given id.
	 */
	public void delete(UUID equipmentId) {
		checkIfNull(equipmentId, "equipment id");
		List<EquipmentPositionHistory> equipmetPositionsToBeDeleted = repository.findAll().stream()
				.filter(equipmentPosition -> equipmentPosition.getEquipmentId().equals(equipmentId))
				.collect(Collectors.toList());
		if (equipmetPositionsToBeDeleted.isEmpty()) {
			throw new EntityNotFoundException("The equipment with the id " + equipmentId
					+ " was not found or does not exist. Please verify if the id is correct.");
		}
		repository.deleteAll(equipmetPositionsToBeDeleted);
	}

}

package machado.antonio.testebackendestagiov3.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import machado.antonio.testebackendestagiov3.dtos.EquipmentPositionHistoryDTO;
import machado.antonio.testebackendestagiov3.dtos.EquipmentStateHistoryDTO;
import machado.antonio.testebackendestagiov3.entities.EquipmentStateHistory;
import machado.antonio.testebackendestagiov3.entities.EquipmentStateHistoryId;
import machado.antonio.testebackendestagiov3.exceptions.CannotRequestNullException;
import machado.antonio.testebackendestagiov3.repositories.EquipmentStateHistoryRepository;

@Service
public class EquipmentStateHistoryService {

	EquipmentStateHistoryRepository repository;
	EquipmentStateHistory equipmentStateHistory;
	EquipmentStateHistoryId equipmentStateHistoryId;
	EquipmentStateService equipmentStateService;

	@Autowired
	public void setRepository(EquipmentStateHistoryRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setEquipmentStateHistory(EquipmentStateHistory equipmentStateHistory) {
		this.equipmentStateHistory = equipmentStateHistory;
	}

	@Autowired
	public void setEquipmentStateHistoryId(EquipmentStateHistoryId equipmentStateHistoryId) {
		this.equipmentStateHistoryId = equipmentStateHistoryId;
	}

	@Autowired
	public void setEquipmentStateService(EquipmentStateService equipmentStateService) {
		this.equipmentStateService = equipmentStateService;
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
	 * @return a list of all {@code equipment state histories} which are present in
	 *         the repository.
	 */
	public List<EquipmentStateHistory> getAll() {
		return repository.findAll();
	}

	/**
	 * @param equipmentId the id of an {@code equipment} to find its
	 *                    {@code state history} from the repository.
	 * @return a list of the {@code equipment state history} requested, else throws
	 *         an {@code exception}.
	 * @throws CannotRequestNullException if the {@code equipment} id is null.
	 * @throws EntityNotFoundException    if no {@code equipment state history} was
	 *                                    found with the given {@code equipment} id.
	 */
	public List<EquipmentStateHistory> getByEquipmentId(UUID equipmentId) {
		checkIfNull(equipmentId, "equipment id");
		List<EquipmentStateHistory> requestedEquipmentStateHistory = repository.findAll().stream()
				.filter(equipmentState -> equipmentState.getId().getEquipmentId().equals(equipmentId))
				.collect(Collectors.toList());
		if (requestedEquipmentStateHistory.isEmpty()) {
			throw new EntityNotFoundException("The equipment with the id " + equipmentId
					+ " was not found or does not exist. Please verify if the id is correct.");
		} else {
			return requestedEquipmentStateHistory;
		}
	}

	/**
	 * @param equipmentId the id of an {@code equipment} to find its most recent
	 *                    {@code state}.
	 * @return the most recent {@code position} of the requested {@code equipment},
	 *         else throws an exception.
	 * @throws CannotRequestNullException if the {@code equipment} id is null.
	 * @throws EntityNotFoundException    if no {@code equipment state history} was
	 *                                    found with the given {@code equipment} id.
	 */
	public EquipmentStateHistory getMostRecentState(UUID equipmentId) {
		checkIfNull(equipmentId, "equipment id");
		/**
		 * Finds all states with corresponding equipment id.
		 */
		List<EquipmentStateHistory> filteredById = repository.findAll().stream()
				.filter(equipmentState -> equipmentState.getId().getEquipmentId().equals(equipmentId))
				.collect(Collectors.toList());
		if (filteredById.isEmpty()) {
			throw new EntityNotFoundException("The equipment with the id " + equipmentId
					+ " was not found or does not exist. Please verify if the id is correct.");
		}
		/**
		 * Finds the most recent state by date.
		 */
		LocalDateTime mostRecentStateTime = filteredById.stream().map(equipmentState -> equipmentState.getDate())
				.max((date1, date2) -> date1.compareTo(date2)).get();
		/**
		 * Finds the requested equipment with the most recent state.
		 */
		EquipmentStateHistory equipmentWithTheMostRecentState = filteredById.stream()
				.filter(equipmentState -> equipmentState.getDate().equals(mostRecentStateTime)).findAny().get();
		return equipmentWithTheMostRecentState;
	}

	/**
	 * @return a list with the most recent states from all {@code equipments} which
	 *         are present in the repository.
	 */
	public List<EquipmentStateHistory> getMostRecentStateFromAll() {
		List<EquipmentStateHistory> listOfMostRecentStateFromAll = repository.findAll().stream().distinct()
				.map(equipmentState -> getMostRecentState(equipmentState.getId().getEquipmentId()))
				.collect(Collectors.toList());
		return listOfMostRecentStateFromAll;
	}

	/**
	 * @param equipmentStateId the id of an {@code equipment state} to find all
	 *                         {@code equipments} which currently have this state.
	 * @return a list of all {@code equipments} which currently have the requested
	 *         state in the repository, else throws an exception.
	 * @throws CannotRequestNullException if the {@code equipment state} id is null.
	 * @throws EntityNotFoundException    if no {@code equipment state history} was
	 *                                    found with the given
	 *                                    {@code equipment state} id.
	 */
	public List<EquipmentStateHistory> getAllByState(UUID equipmentStateId) {
		checkIfNull(equipmentStateId, "equipment state id");
		List<EquipmentStateHistory> listOfEquipmensByRequestedState = getMostRecentStateFromAll().stream()
				.filter(equipmentState -> equipmentState.getId().getEquipmentStateId().equals(equipmentStateId))
				.collect(Collectors.toList());
		if (listOfEquipmensByRequestedState.isEmpty()) {
			throw new EntityNotFoundException("The equipment state with the id " + equipmentStateId
					+ " was not found  or no equipment has the requested state or the equipment state id does not exist."
					+ " Please verify if the id is correct.");
		} else {
			return listOfEquipmensByRequestedState;
		}
	}

	/**
	 * @param newStateForEquipment the new current {@code state} of an equipment as
	 *                             an {@link EquipmentPositionHistoryDTO}.
	 * @return the new {@code equipment state} added in the repository by this
	 *         request, else throws an {@code exception}.
	 * @throws CannotRequestNullException if the new {@code equipment state} or its
	 *                                    fields are null (like equipment id and
	 *                                    equipment state id).
	 * @throws EntityNotFoundException    if no {@code equipment} was found with the
	 *                                    given id or if no {@code equipment state}
	 *                                    was found in the {@code equipment state}
	 *                                    repository.
	 */
	public EquipmentStateHistory addStateForEquipment(EquipmentStateHistoryDTO newStateForEquipment) {
		checkIfNull(newStateForEquipment, "equipment state history");
		checkIfNull(newStateForEquipment.getEquipmentId(), "equipment id");
		checkIfNull(newStateForEquipment.getEquipmentStateId(), "equipment state id");

		boolean foundEquipment = repository.findAll().stream().anyMatch(equipmentState -> equipmentState.getId()
				.getEquipmentId().equals(newStateForEquipment.getEquipmentId()));
		if (!foundEquipment) {
			throw new EntityNotFoundException("The equipment with the id " + newStateForEquipment.getEquipmentId()
					+ " was not found or does not exist. Please verify if the id is correct.");
		}
		boolean foundEquipmentState = equipmentStateService
				.checkIfExistsById(newStateForEquipment.getEquipmentStateId());
		if (!foundEquipmentState) {
			throw new EntityNotFoundException("The equipment state id " + newStateForEquipment.getEquipmentStateId()
					+ " was not found or does not exist. Please verify if the id is correct.");
		}
		equipmentStateHistoryId.setEquipmentId(newStateForEquipment.getEquipmentId());
		equipmentStateHistoryId.setEquipmentStateId(newStateForEquipment.getEquipmentStateId());
		equipmentStateHistory.setId(equipmentStateHistoryId);
		equipmentStateHistory.setDate(LocalDateTime.now());
		return repository.save(equipmentStateHistory);
	}

	/**
	 * @param equipmentStateToBeEdited the {@code equipment state} with the state
	 *                                 edited and the requested date.
	 * @return the edited {@code equipment state}, else throws an {@code exception}.
	 * 
	 * @throws CannotRequestNullException if the edited {@code equipment state} or
	 *                                    its fields are null (like equipment id and
	 *                                    equipment state id).
	 * @throws EntityNotFoundException    if no {@code equipment state} was found
	 *                                    with the given id or with the given date.
	 */
	public EquipmentStateHistory edit(EquipmentStateHistory equipmentStateToBeEdited) {
		/**
		 * Checks:
		 */
		checkIfNull(equipmentStateToBeEdited, "equipment state history");
		checkIfNull(equipmentStateToBeEdited.getId(), "equipment state id");
		checkIfNull(equipmentStateToBeEdited.getId().getEquipmentId(), "equipment id");
		checkIfNull(equipmentStateToBeEdited.getId().getEquipmentStateId(), "equipment state id");
		checkIfNull(equipmentStateToBeEdited.getDate(), "equipment state date");
		boolean foundEquipmentState = equipmentStateService
				.checkIfExistsById(equipmentStateToBeEdited.getId().getEquipmentStateId());
		if (!foundEquipmentState) {
			throw new EntityNotFoundException(
					"The equipment state id " + equipmentStateToBeEdited.getId().getEquipmentStateId()
							+ " was not found or does not exist. Please verify if the id is correct.");
		}

		Optional<EquipmentStateHistory> equipmentWithRequestedDate = repository.findAll().stream()
				.filter(equipmentState -> equipmentState.getId().getEquipmentId()
						.equals(equipmentStateToBeEdited.getId().getEquipmentId())
						&& equipmentState.getDate().equals(equipmentStateToBeEdited.getDate()))
				.findAny();
		if (equipmentWithRequestedDate.isEmpty()) {
			throw new EntityNotFoundException(
					"The equipment with the id " + equipmentStateToBeEdited.getId().getEquipmentId()
							+ " with the equipment state date " + equipmentStateToBeEdited.getDate()
							+ " was not found or does not exist. Please verify if the ids and date are correct.");
		}
		equipmentStateHistoryId.setEquipmentId(equipmentStateToBeEdited.getId().getEquipmentId());
		equipmentStateHistoryId.setEquipmentStateId(equipmentStateToBeEdited.getId().getEquipmentStateId());
		equipmentStateHistory.setId(equipmentStateHistoryId);
		equipmentStateHistory.setDate(equipmentStateToBeEdited.getDate());
		return repository.save(equipmentStateHistory);
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
		List<EquipmentStateHistory> equipmentStatesToBeDeleted = repository.findAll().stream()
				.filter(equipmentState -> equipmentState.getId().getEquipmentId().equals(equipmentId))
				.collect(Collectors.toList());
		if (equipmentStatesToBeDeleted.isEmpty()) {
			throw new EntityNotFoundException("The equipment with the id " + equipmentId
					+ " was not found or does not exist. Please verify if the id is correct.");
		} else {
			repository.deleteAll(equipmentStatesToBeDeleted);
		}
	}

}

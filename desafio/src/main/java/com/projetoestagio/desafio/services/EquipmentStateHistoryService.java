package com.projetoestagio.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetoestagio.desafio.entities.EquipmentStateHistory;
import com.projetoestagio.desafio.repositories.EquipmentStateHistoryRepository;
import com.projetoestagio.desafio.services.exceptions.DatabaseException;
import com.projetoestagio.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipmentStateHistoryService {
	@Autowired
	private EquipmentStateHistoryRepository repository;
	
	public List<EquipmentStateHistory> findAll(){
		return repository.findAll();
	}
	
	public EquipmentStateHistory findById(Long id) {
		Optional<EquipmentStateHistory> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public EquipmentStateHistory insert(EquipmentStateHistory obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	public EquipmentStateHistory update(Long id, EquipmentStateHistory obj) {
		try {
			EquipmentStateHistory entity = repository.getReferenceById(id);
			updateData(entity,obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(EquipmentStateHistory entity, EquipmentStateHistory obj) {
		entity.setDate(obj.getDate());
		
	}
	
	
}

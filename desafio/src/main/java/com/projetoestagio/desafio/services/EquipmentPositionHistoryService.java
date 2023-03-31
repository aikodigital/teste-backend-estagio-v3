package com.projetoestagio.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetoestagio.desafio.entities.Equipment;
import com.projetoestagio.desafio.entities.EquipmentPositionHistory;
import com.projetoestagio.desafio.repositories.EquipmentPositionHistoryRepository;
import com.projetoestagio.desafio.services.exceptions.DatabaseException;
import com.projetoestagio.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipmentPositionHistoryService {
	@Autowired
	private EquipmentPositionHistoryRepository repository;
	
	public List<EquipmentPositionHistory> findAll(){
		return repository.findAll();
	}
	
	public EquipmentPositionHistory findById(Long id) {
		Optional<EquipmentPositionHistory> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public EquipmentPositionHistory insert(EquipmentPositionHistory obj) {
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
	public EquipmentPositionHistory update(Long id, EquipmentPositionHistory obj) {
		try {
			EquipmentPositionHistory entity = repository.getReferenceById(id);
			updateData(entity,obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(EquipmentPositionHistory entity, EquipmentPositionHistory obj) {
		entity.setLat(obj.getLat());	
		entity.setLon(obj.getLon());
		entity.setDate(obj.getDate());
	}
	
	
}

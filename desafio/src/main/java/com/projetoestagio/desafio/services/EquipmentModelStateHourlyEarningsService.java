package com.projetoestagio.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetoestagio.desafio.entities.EquipmentModelStateHourlyEarnings;
import com.projetoestagio.desafio.repositories.EquipmentModelStateHourlyEarningsRepository;
import com.projetoestagio.desafio.services.exceptions.DatabaseException;
import com.projetoestagio.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipmentModelStateHourlyEarningsService {
	@Autowired
	private EquipmentModelStateHourlyEarningsRepository repository;
	
	public List<EquipmentModelStateHourlyEarnings> findAll(){
		return repository.findAll();
	}
	
	public EquipmentModelStateHourlyEarnings findById(Long id) {
		Optional<EquipmentModelStateHourlyEarnings> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public EquipmentModelStateHourlyEarnings insert(EquipmentModelStateHourlyEarnings obj) {
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
	public EquipmentModelStateHourlyEarnings update(Long id, EquipmentModelStateHourlyEarnings obj) {
		try {
			EquipmentModelStateHourlyEarnings entity = repository.getReferenceById(id);
			updateData(entity,obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(EquipmentModelStateHourlyEarnings entity, EquipmentModelStateHourlyEarnings obj) {
		entity.setValue(obj.getValue());
		
	}
	
	
}

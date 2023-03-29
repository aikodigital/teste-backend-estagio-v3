package com.projetoestagio.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetoestagio.desafio.entities.Equipment;
import com.projetoestagio.desafio.entities.EquipmentModel;
import com.projetoestagio.desafio.repositories.EquipmentModelRepository;
import com.projetoestagio.desafio.services.exceptions.DatabaseException;
import com.projetoestagio.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipmentModelService {
	@Autowired
	private EquipmentModelRepository repository;
	
	public List<EquipmentModel> findAll(){
		return repository.findAll();
	}
	
	public EquipmentModel findById(Long id) {
		Optional<EquipmentModel> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public EquipmentModel insert(EquipmentModel obj) {
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
	public EquipmentModel update(Long id, EquipmentModel obj) {
		try {
			EquipmentModel entity = repository.getReferenceById(id);
			updateData(entity,obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(EquipmentModel entity, EquipmentModel obj) {
		entity.setName(obj.getName());		
	}
	
	
}

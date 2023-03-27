package br.com.aikodigital.api.repositorys;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.aikodigital.api.models.Equipment_State;

@Repository
public interface Equipment_State_Repository extends CrudRepository<Equipment_State, UUID> {
    
}

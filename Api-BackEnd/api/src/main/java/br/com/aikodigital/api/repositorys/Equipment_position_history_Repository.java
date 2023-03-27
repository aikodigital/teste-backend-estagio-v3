package br.com.aikodigital.api.repositorys;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.aikodigital.api.models.Equipment;
import br.com.aikodigital.api.models.Equipment_position_history;

@Repository
public interface Equipment_position_history_Repository extends CrudRepository<Equipment_position_history, UUID>{
    
}

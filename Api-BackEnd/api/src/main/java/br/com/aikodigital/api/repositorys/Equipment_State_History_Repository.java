package br.com.aikodigital.api.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.aikodigital.api.models.Equipment_State_History;
import br.com.aikodigital.api.models.Equipment_State_History_ID;

@Repository
public interface Equipment_State_History_Repository extends CrudRepository<Equipment_State_History, Equipment_State_History_ID>{
    
}

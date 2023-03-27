package br.com.aikodigital.api.repositorys;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.aikodigital.api.models.Equipment_Model;

@Repository
public interface Equipment_ModelRepository extends CrudRepository<Equipment_Model, UUID> {
    
}

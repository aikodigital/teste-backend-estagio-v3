package br.com.aikodigital.api.repositorys;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.aikodigital.api.models.Equipment_MSHE;
import br.com.aikodigital.api.models.Equipment_MSHE_ID;

@Repository
public interface Equipment_MSHE_Repository extends CrudRepository<Equipment_MSHE, Equipment_MSHE_ID> {
    
}

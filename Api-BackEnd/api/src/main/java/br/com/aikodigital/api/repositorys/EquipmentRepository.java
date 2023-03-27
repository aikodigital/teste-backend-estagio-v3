package br.com.aikodigital.api.repositorys;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.aikodigital.api.models.Equipment;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, UUID> {
    
}

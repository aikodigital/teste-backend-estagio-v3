package aelton.teste.teste.estagio.service;

import aelton.teste.teste.estagio.entity.Equipment;
import aelton.teste.teste.estagio.repository.equipamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class equipmentService  {
    @Autowired
    private final equipamentRepository repository;


    public equipmentService(equipamentRepository repository) {
        this.repository = repository;

    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Equipment> findAll(){
        return repository.findAll();

    }

    @Transactional(readOnly = true)
    public Equipment findById(Long id) {
        return repository.findById(id).orElse(null);

    }

    @Transactional

    public Equipment create(Equipment equipment) {

        equipment.setId(equipment.getId());
        equipment.setEquipmentModelId(equipment.getEquipmentModelId());
        equipment.setName(equipment.getName());
        equipment.setEquipmentModel(equipment.getEquipmentModel());



        return repository.save(equipment);

    }
    @Transactional
    public Equipment update(@PathVariable Long id,Equipment equipmentCreate) {
        Equipment equipment = findById(id);
        equipment.setName(equipmentCreate.getName());
        equipment.setEquipmentModelId(equipmentCreate.getEquipmentModelId());
        repository.save(equipment);
        return equipment;
    }
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}

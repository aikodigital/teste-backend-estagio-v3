package aelton.teste.teste.estagio.service;

import aelton.teste.teste.estagio.entity.EquipmentModel;
import aelton.teste.teste.estagio.repository.equipmentModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
public class equipmentModelService {
    @Autowired
    private final equipmentModelRepository equipmentModelRepository;

    public equipmentModelService(equipmentModelRepository equipmentModelRepository) {
        this.equipmentModelRepository = equipmentModelRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<EquipmentModel> findAll(){
        return equipmentModelRepository.findAll();

    }

    @Transactional(readOnly = true)
    public EquipmentModel findById(Long id) {
        return equipmentModelRepository.findById(id).orElse(null);

    }

    @Transactional
    public EquipmentModel create(EquipmentModel equipmentModel) {

        equipmentModel.setId(equipmentModel.getId());
        equipmentModel.setName(equipmentModel.getName());
        return equipmentModelRepository.save(equipmentModel);

    }
    @Transactional
    public EquipmentModel update(@PathVariable Long id, EquipmentModel equipmentCreate) {
        EquipmentModel equipmentModel = findById(id);
        equipmentModel.setName(equipmentCreate.getName());
        equipmentModelRepository.save(equipmentModel);
        return equipmentModel;
    }
    @Transactional
    public void delete(Long id) {
        findById(id);
        equipmentModelRepository.deleteById(id);
    }
}

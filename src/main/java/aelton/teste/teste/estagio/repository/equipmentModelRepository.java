package aelton.teste.teste.estagio.repository;

import aelton.teste.teste.estagio.entity.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface equipmentModelRepository extends JpaRepository<EquipmentModel, Long> {
}

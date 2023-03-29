package machado.antonio.testebackendestagiov3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import machado.antonio.testebackendestagiov3.entities.EquipmentModelStateHourlyEarnings;
import machado.antonio.testebackendestagiov3.entities.EquipmentModelStateHourlyEarningsId;

@Repository
public interface EquipmentModelStateHourlyEarningsRepository
		extends JpaRepository<EquipmentModelStateHourlyEarnings, EquipmentModelStateHourlyEarningsId> {

}

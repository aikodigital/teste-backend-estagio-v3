package Aiko.Desafio.repo;

import Aiko.Desafio.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {
}

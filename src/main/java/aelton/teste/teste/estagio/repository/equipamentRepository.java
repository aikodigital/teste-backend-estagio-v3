package aelton.teste.teste.estagio.repository;

import aelton.teste.teste.estagio.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface equipamentRepository extends JpaRepository<Equipment, Long> {
}

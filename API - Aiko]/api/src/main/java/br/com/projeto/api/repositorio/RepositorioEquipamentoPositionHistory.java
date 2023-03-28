package br.com.projeto.api.repositorio;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import br.com.projeto.api.model.EquipamentoPositionHistory;
import org.springframework.stereotype.Repository;
@Repository
public interface RepositorioEquipamentoPositionHistory extends CrudRepository<EquipamentoPositionHistory, UUID>
{}    


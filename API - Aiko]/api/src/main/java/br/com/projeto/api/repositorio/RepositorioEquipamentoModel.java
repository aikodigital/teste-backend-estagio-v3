package br.com.projeto.api.repositorio;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.projeto.api.model.EquipamentoModel;
@Repository
public interface RepositorioEquipamentoModel extends CrudRepository<EquipamentoModel, UUID>{}

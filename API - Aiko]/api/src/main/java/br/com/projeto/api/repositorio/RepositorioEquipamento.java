package br.com.projeto.api.repositorio;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import br.com.projeto.api.model.Equipamento;
import org.springframework.stereotype.Repository;
@Repository
public interface RepositorioEquipamento extends CrudRepository<Equipamento, UUID>
{}

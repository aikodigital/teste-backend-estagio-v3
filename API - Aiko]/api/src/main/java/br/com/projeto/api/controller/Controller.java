package br.com.projeto.api.controller;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.projeto.api.model.Equipamento;
import br.com.projeto.api.model.EquipamentoModel;
import br.com.projeto.api.model.EquipamentoModelHour;
import br.com.projeto.api.model.EquipamentoPositionHistory;
import br.com.projeto.api.model.EquipamentoState;
import br.com.projeto.api.model.EquipamentoStateHistory;
import br.com.projeto.api.repositorio.RepositorioEquipamento;
import br.com.projeto.api.repositorio.RepositorioEquipamentoState;
import br.com.projeto.api.repositorio.RepositorioEquipamentoModel;
import br.com.projeto.api.repositorio.RepositorioEquipamentoHour;
import br.com.projeto.api.repositorio.RepositorioEquipamentoPositionHistory;
import br.com.projeto.api.repositorio.RepositorioEquipamentoStateHistory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller 
{
    //#region Equipamento
    @Autowired
    private RepositorioEquipamento acao;
    @PostMapping("/saveEquip")
    public Equipamento cadastrarEquipamento(@RequestBody Equipamento eq)
    {
        return acao.save(eq);
    }
    @GetMapping("/listaEquip")
    public List<Equipamento> selectAllEquipamentos()
    {
        return (List<Equipamento>) acao.findAll();
    }
    @GetMapping("/listaEquip/{id}")
    public Optional<Equipamento> selectEquipamentoByID(@PathVariable UUID id)
    {
        return acao.findById(id);
    }
    @PutMapping("/listaEquip")
    public Equipamento editarEquipamento(@RequestBody Equipamento obj)
    {
        return acao.save(obj);
    }
    @DeleteMapping("/listaEquip/{id}")
    public void deleteEquipamento(@PathVariable UUID id)
    {
        acao.deleteById(id);
    }
    //#endregion Equipamento

    //#region EquipamentoState
    @Autowired
    private RepositorioEquipamentoState acao2;

    @PostMapping("/savestate")
    public EquipamentoState cadastrarState(@RequestBody EquipamentoState eqs)
    {
        return acao2.save(eqs);
    }
    @GetMapping("/listastate")
    public List<EquipamentoState> selectAllStates()
    {
        return (List<EquipamentoState>) acao2.findAll();
    }
    @GetMapping("/listastate/{id}")
    public Optional<EquipamentoState> selectStatebyId(@PathVariable UUID id)
    {
        return acao2.findById(id);
    }
    @PutMapping("/listastate")
    public EquipamentoState editarState(@RequestBody EquipamentoState eqs)
    {
        return acao2.save(eqs);
    }
    @DeleteMapping("/listastate/{id}")
    public void deleteState(@PathVariable UUID id)
    {
        acao2.deleteById(id);
    }
    //#endregion EquipamentoState

    //#region EquipamentoModel
    @Autowired
    private RepositorioEquipamentoModel acao3;

    @PostMapping("/savemodel")
    public EquipamentoModel cadastrarModel(@RequestBody EquipamentoModel eqm)
    {
        return acao3.save(eqm);
    }
    @GetMapping("/listamodel")
    public List<EquipamentoModel> selectAllModels()
    {
        return (List<EquipamentoModel>) acao3.findAll();
    }
    @GetMapping("/listamodel/{id}")
    public Optional<EquipamentoModel> selectModelbyID(@PathVariable UUID id)
    {
        return acao3.findById(id);
    }
    @PutMapping("/listamodel")
    public EquipamentoModel editarModel(@RequestBody EquipamentoModel eqm)
    {
        return acao3.save(eqm);
    }
    @DeleteMapping("/listamodel/{id}")
    public void deleteModel(@PathVariable UUID id)
    {
        acao3.deleteById(id);
    }
    //#endregion EquipamentoModel

    //#region EquipamentoPositionHistory
    @Autowired
    private RepositorioEquipamentoPositionHistory acao4;

    @PostMapping("/savePosition")
    public EquipamentoPositionHistory cadastrarPosition(@RequestBody EquipamentoPositionHistory eqph)
    {
        return acao4.save(eqph);
    }
    @GetMapping("/listaPosition")
    public List<EquipamentoPositionHistory> selectallPositions()
    {
        return (List<EquipamentoPositionHistory>) acao4.findAll();
    }
    @GetMapping("/listaPosition/{equipment_id}")
    public Optional<EquipamentoPositionHistory> selectPositionbyID(@PathVariable UUID equipment_id)
    {
        return acao4.findById(equipment_id);
    }
    @PutMapping("/listaPosition")
    public EquipamentoPositionHistory editarPosition(@RequestBody EquipamentoPositionHistory eqph)
    {
        return acao4.save(eqph);
    }
    @DeleteMapping("/listaPosition/{id}")
    public void deletePosition(@PathVariable UUID id)
    {
        acao4.deleteById(id);
    }
    //#endregion EquipamentoPositionHistory

    //#region EquipamentoStateHistory
    @Autowired
    private RepositorioEquipamentoStateHistory acao5;

    @PostMapping("/savestatehistory")
    public EquipamentoStateHistory cadastrarStateHistory(@RequestBody EquipamentoStateHistory eqsh)
    {
        return acao5.save(eqsh);
    }
    @GetMapping("/listastatehistory")
    public List<EquipamentoStateHistory> selectAllStatesHistory()
    {
        return (List<EquipamentoStateHistory>) acao5.findAll();
    }
    @GetMapping("/listastatehistory/{id}")
    public Optional<EquipamentoStateHistory> selectStateHistorybyId(@PathVariable UUID equipment_id)
    {
        return acao5.findById(equipment_id);
    }
    @PutMapping("/listastatehistory")
    public EquipamentoStateHistory editarStateHistory(@RequestBody EquipamentoStateHistory eqsh)
    {
        return acao5.save(eqsh);
    }
    @DeleteMapping("/listastatehistory/{id}")
    public void deleteStateHistory(@PathVariable UUID equipment_id)
    {
        acao5.deleteById(equipment_id);
    }
    //#endregion EquipamentoStateHistory

    //#region EquipamentoModelHourlyEarnings
    @Autowired
    private RepositorioEquipamentoHour acao6;

    @PostMapping("/savemodelhour")
    public EquipamentoModelHour cadastrarHourlyEarning(@RequestBody EquipamentoModelHour eqhe)
    {
        return acao6.save(eqhe);
    }
    @GetMapping("/listamodelhour")
    public List<EquipamentoModelHour> selectAllHourlyEranigs()
    {
        return (List<EquipamentoModelHour>) acao6.findAll();
    }
    @GetMapping("/listamodelhour/{equipment_model_id}")
    public Optional<EquipamentoModelHour> selectHourlyEarningbyID(@PathVariable UUID equipment_model_id)
    {
        return acao6.findById(equipment_model_id);
    }
    @PutMapping("/listamodelhour")
    public EquipamentoModelHour editHourlyEarning(@RequestBody EquipamentoModelHour eqhe)
    {
        return acao6.save(eqhe);
    }
    @DeleteMapping("/listastatehistory/{equipment_model_id}")
    public void deleteHourEarning(@PathVariable UUID equipment_model_id)
    {
        acao6.deleteById(equipment_model_id);
    }
    //#endregion EquipamentoModelHourlyEarnings
}

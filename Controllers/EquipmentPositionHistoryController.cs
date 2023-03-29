using API_CRUD_Test.Models;
using API_CRUD_Test.Repository;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace API_CRUD_Test.Controllers
{
    [Route("api/[controller]")]
    public class EquipmentPositionHistoryController : ControllerBase
    {
        // Criamos uma Injeção de Dependencia aqui para chamar o _context
        private readonly IEquipmentPositionHistory _equipmentPositionHistoryRepository;
        public EquipmentPositionHistoryController(IEquipmentPositionHistory equipmentStateRepository)
        {
            _equipmentPositionHistoryRepository = equipmentStateRepository;
        }

        [HttpPost("Add")]

        public IActionResult Create(EquipmentPositionHistory equipment)
        {
            // Faz a atualização
            try
            {
                //Tenta adicionar ao BD
                _equipmentPositionHistoryRepository.Add(equipment);
                return Ok("Posição adicionada ao histórico com sucesso!");
            }
            // Chama a exceção caso não consiga
            catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao adicionar a posição do Equipamento ao histórico:\n{ex.Message}"); // Chama a exceção
            }
        }
        [HttpPost("Remove")]
        public IActionResult Remove(Guid equipId,DateTime date, [FromQuery] bool confirmRemove = false)
        {
            if (confirmRemove)
            {
                try
                {
                    _equipmentPositionHistoryRepository.Remove(equipId, date);
                    return Ok("Posição removida do histórico com sucesso.");
                }
                catch (Exception ex)
                {
                    return Ok($"Ocorreu ao remover posição do histórico:\n{ex.Message}");
                }

            }
            else
            {
                return BadRequest("É necessário confirmar a remoção!");
            }
        }

        [HttpPost("Edit")]
        public IActionResult Edit(EquipmentPositionHistory equipment)
        {
            try
            {
                _equipmentPositionHistoryRepository.Edit(equipment);
                return Ok("Histórico editado com sucesso");
            }
            catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao editar o Histórico de Posições\n{ex.Message}");
            }
        }

        [HttpGet("View")]
        public IActionResult View()
        {
            List<EquipmentPositionHistory> equipmentPositionList = _equipmentPositionHistoryRepository.GetAllEquipmentPositionHistory();
            return Ok(equipmentPositionList);

        }

        [HttpGet ("CurrentPosition")]
        public IActionResult CurrentPosition(Guid id)
        {
            try
            {
                var currentPosition = _equipmentPositionHistoryRepository.CurrentPosition(id);
                return Ok(currentPosition);
            }
            catch (Exception ex)
            {
                return BadRequest($"Ocorreu um erro:\n{ex.Message}");
            }
        }

    }
}


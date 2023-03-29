using API_CRUD_Test.Models;
using API_CRUD_Test.Repository;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace API_CRUD_Test.Controllers
{
    [Route("api/[controller]")]
    public class EquipmentStateHistoryController : ControllerBase
    {
        private readonly IEquipmentStateHistory _equipmentStateHistoryRepository;

        public EquipmentStateHistoryController(IEquipmentStateHistory equipmentRepository)
        {
            _equipmentStateHistoryRepository = equipmentRepository;
        }

        [HttpPost("Add")]

        public IActionResult Create(EquipmentStateHistory equipment)
        {
            try
            { 
                _equipmentStateHistoryRepository.Add(equipment);
                return Ok("Estado do equipamento adicionado ao histórico!");
            }
            catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao adicionar o Estado do Equipamento ao histórico\n{ex.Message}");
            }
        }

        [HttpPost("Edit")]
        public IActionResult Edit(EquipmentStateHistory equipment)
        {
            try
            {
                _equipmentStateHistoryRepository.Edit(equipment);
                return Ok("Histórico de Estado Equipamento Editado com Sucesso");
            }
            catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao editar o histórico do Estado do Equipamento\n{ex.Message}");
            }

        }

        [HttpPost("Remove")]
        public IActionResult Remove(Guid equipId, DateTime date, [FromQuery] bool confirmRemove = false)
        {
            if (confirmRemove)
            {
                try
                {
                    _equipmentStateHistoryRepository.Remove(equipId, date);
                    return Ok("Estado do equipamento removido do histórico!");
                }
                catch (Exception ex)
                {
                    return Ok($"Ocorreu um erro ao adicionar o modelo do Equipamento\n{ex.Message}");
                }

            }
            else
            {
                return BadRequest("É necessário confirmar a remoção!");
            }
        }

        [HttpGet("View")]
        public IActionResult View()
        {
            List<EquipmentStateHistory> equipmentStateHistoryList = _equipmentStateHistoryRepository.GetAllEquipmentStateHistory();
            return Ok(equipmentStateHistoryList);
        }

        [HttpGet("CurrentState")]
        public IActionResult CurrentState(Guid id)
        {
            try
            {
                var currentState = _equipmentStateHistoryRepository.CurrentState(id);
                return Ok(currentState);
            }
            catch (Exception ex)
            {
                return BadRequest($"Ocorreu um erro:\n{ex.Message}");
            }
        }
    }
}

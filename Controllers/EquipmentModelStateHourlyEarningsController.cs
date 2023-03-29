using API_CRUD_Test.Models;
using API_CRUD_Test.Repository;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace API_CRUD_Test.Controllers
{
    [Route("api/[controller]")]
    public class EquipmentModelStateHourlyEarningsController : ControllerBase
    {
        private readonly IEquipmentModelStateHourlyEarnings _equipmentModelStateHourlyEarningsRepository;

        public EquipmentModelStateHourlyEarningsController(IEquipmentModelStateHourlyEarnings equipmentRepository)
        {
            _equipmentModelStateHourlyEarningsRepository = equipmentRepository;
        }
        [HttpPost("Add")]

        public IActionResult Create(EquipmentModelStateHourlyEarnings equipment)
        {
            try
            {
                _equipmentModelStateHourlyEarningsRepository.Add(equipment);
                return Ok("Ganho por Hora foi adicionado a um novo modelo!");
            }
            catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao adicionar os dados\n{ex.Message}");
            }
        }
        [HttpPost("Remove")]
        public IActionResult Remove(Guid modelId,Guid stateId,  [FromQuery] bool confirmRemove = false)
        {
            if (confirmRemove)
            {
                try
                {
                    _equipmentModelStateHourlyEarningsRepository.Remove(modelId, stateId);
                    return Ok("Dados removidos com sucesso!");
                }
                catch (Exception ex)
                {
                    return Ok($"Ocorreu um erro ao adicionar os dados\n{ex.Message}");
                }

            }
            else
            {
                return BadRequest("É necessário confirmar a remoção!");
            }
        }
        [HttpPost("Edit")]
        public IActionResult Edit(EquipmentModelStateHourlyEarnings equipment)
        {
            try
            {
                _equipmentModelStateHourlyEarningsRepository.Edit(equipment);
                return Ok("Valores editados com sucesso");
            }
            catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao editar os valores\n{ex.Message}");
            }

        }
        [HttpGet("View")]
        public IActionResult View()
        {
            List<EquipmentModelStateHourlyEarnings> equipmentStateHourlyList = _equipmentModelStateHourlyEarningsRepository.GetAllEquipmentModelStateHourlyEarnings();
            return Ok(equipmentStateHourlyList);
        }
    }
}

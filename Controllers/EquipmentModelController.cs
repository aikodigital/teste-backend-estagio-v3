using API_CRUD_Test.Repository.Interfaces;
using Microsoft.AspNetCore.Mvc;
using API_CRUD_Test.Models;

namespace API_CRUD_Test.Controllers
{
    [Route("api/[controller]")]
    public class EquipmentModelController : ControllerBase
    {
        // Criamos uma Injeção de Dependencia aqui para chamar o _context
        private readonly IEquipmentModel _equipmentModelRepository;
        public EquipmentModelController(IEquipmentModel equipmentModelRepository)
        {
            _equipmentModelRepository = equipmentModelRepository;
        }
        //-------
        [HttpPost("Add")]

        public IActionResult Create(EquipmentModel equipment)
        {
            try
            {
                _equipmentModelRepository.Add(equipment);
                return Ok("Modelo de Equipamento Adicionado com Sucesso!");
            }
            catch(Exception ex){
                return Ok($"Ocorreu um erro ao adicionar o Modelo de Equipamento\n{ex.Message}"); 
        }
        }

        [HttpPost("Remove")]
        public IActionResult Remove(Guid id, [FromQuery] bool confirmRemove = false)
        {
            if (confirmRemove)
            {
                try
                {
                    _equipmentModelRepository.Remove(id);
                    return Ok("Modelo de equipamento Removido com Sucesso!");
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

        [HttpPost("Edit")]
        public IActionResult Edit(EquipmentModel equipment)
        {
            try
            {
                _equipmentModelRepository.Edit(equipment);
                return Ok("Modelo de Equipamento Editado com Sucesso");
            }
            catch(Exception ex){
                return Ok($"Ocorreu um erro ao adicionar o Modelo de Equipamento\n{ex.Message}");
    }
}
        [HttpGet("View")]
        public IActionResult View()
        {
            List<EquipmentModel> equipmentModelList = _equipmentModelRepository.GetAllEquipmentModel();
            return Ok(equipmentModelList);

            }
            //-0--
        }
    }

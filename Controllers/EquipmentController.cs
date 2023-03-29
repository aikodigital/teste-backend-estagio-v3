using API_CRUD_Test.Models;
using Microsoft.AspNetCore.Mvc;
using API_CRUD_Test.Repository.Interfaces;
using System.Runtime.Serialization;
using API_CRUD_Test.Repository;

namespace API_CRUD_Test.Controllers
{
    [Route("api/[controller]")]
    public class EquipmentController : ControllerBase
    {
        // Criamos uma Injeção de Dependencia aqui para chamar o _context
        private readonly IEquipment _equipmentRepository;

        public EquipmentController(IEquipment equipmentRepository)
        {
            _equipmentRepository = equipmentRepository;
        }
        //-----------

        [HttpPost("Add")]
        public IActionResult Create(Equipment equipment)
        {
            try
            {
                _equipmentRepository.Add(equipment);
                return Ok("Equipamento Adicionado com Sucesso!");
            }
            catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao adicionar o Controlador do Equipamento\n{ex.Message}");
            }
        }


        [HttpPost("Remove")]
        public IActionResult Remove(Guid id, [FromQuery] bool confirmRemove = false)
        {
            if (confirmRemove)
            {
                try
                {
                    _equipmentRepository.Remove(id);
                    return Ok("Equipamento Removido com Sucesso!");
                }
                catch (Exception ex)
                {
                    return Ok($"Ocorreu um erro ao remover o Estado do Equipamento\n{ex.Message}");
                }
            }
            else
            {
                return BadRequest("É necessário confirmar a remoção!");
            }
        }



        [HttpPost ("Edit")]
        public IActionResult Edit(Equipment equipment) {
            try
            {
                _equipmentRepository.Edit(equipment);
                return Ok("Equipmento Editado com Sucesso");
            }catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao remover o Estado do Equipamento\n{ex.Message}");
            }

        }

        [HttpGet("View")]
        public IActionResult View()
        {
            List<Equipment> equipmentList = _equipmentRepository.GetAllEquipment();
            return Ok(equipmentList);
        }
        //-0--
    }
}

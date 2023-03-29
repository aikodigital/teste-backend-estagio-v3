using API_CRUD_Test.Models;
using API_CRUD_Test.Repository;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace API_CRUD_Test.Controllers
{ 
[Route("api/[controller]")]
        public class EquipmentStateController : ControllerBase
        {
        // Criamos uma Injeção de Dependencia aqui para chamar o _context
        private readonly IEquipmentState _equipmentStateRepository;
        public EquipmentStateController(IEquipmentState equipmentStateRepository)
            {
                _equipmentStateRepository = equipmentStateRepository;
            }
        //-------


        //---
        [HttpPost("Add")]

        public IActionResult Create(EquipmentState equipment)
            {
            // Faz a atualização
            try 
            {
                //Tenta adicionar ao BD
                _equipmentStateRepository.Add(equipment); 
                return Ok("Estado de Equipamento Adicionado com Sucesso!");
            }
            // Chama a exceção caso não consiga
            catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao adicionar o Estado do Equipamento\n{ex.Message}"); // Chama a exceção
            }
            }
        [HttpPost("Remove")]
        public IActionResult Remove(Guid id, [FromQuery] bool confirmRemove = false)
        {
            if (confirmRemove)
            {
                try
                {
                    _equipmentStateRepository.Remove(id);
                    return Ok("Estado do Equipamento Removido com Sucesso!");
                }
                catch (Exception ex)
                {
                    return Ok($"Ocorreu um erro ao adicionar o Estado do Equipamento\n{ex.Message}");
                }

            }
            else
            {
                return BadRequest("É necessário confirmar a remoção!");
            }
        }

        [HttpPost("Edit")]
        public IActionResult Edit(EquipmentState equipment)
        {
            try
            {
                _equipmentStateRepository.Edit(equipment);
                return Ok("Estado do Equipamento Editado com Sucesso");
            }
            catch (Exception ex)
            {
                return Ok($"Ocorreu um erro ao editar o Estado do Equipamento\n{ex.Message}");
            }
        }

        [HttpGet("View")]
        public IActionResult View()
        {
            List<EquipmentState> equipmentStateList = _equipmentStateRepository.GetAllEquipmentState();
            return Ok(equipmentStateList);

        }

    }
    }


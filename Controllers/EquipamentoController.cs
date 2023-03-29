using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Prova.Repositorio.Modelos;
using Prova1.Dto;
using Prova1.Servico.Interfaces;

namespace Prova1.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EquipamentoController : ControllerBase
    {

        private readonly IEquipamentoService _equipamentoService;

        public EquipamentoController(IEquipamentoService equipamentoService)
        {
            _equipamentoService = equipamentoService;
        }

        [HttpPost]
        public async Task<IActionResult> AddEquipamentoAsync([FromBody] EquipamentoDto equipamentoDto)
        {
            await _equipamentoService.AddEquipamentoAsync(new Equipamento()
            {
                Nome = equipamentoDto.Nome,
                EquipmentModelId = equipamentoDto.EquipmentModelId,
            });
            return Ok();
        }
        [HttpGet("Retornar/{id}")]
        public async Task<IActionResult> LerEquipamentoAsync(int id)
        {
            var equipamento = await _equipamentoService.LerEquipamentoAsync(id);
            return Ok(equipamento);
        }


    }
}

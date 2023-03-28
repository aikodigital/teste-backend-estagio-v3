using Microsoft.AspNetCore.Mvc;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipamento;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;

namespace TesteTecnico.Application.Controllers;

[ApiController]
[Route("/api/equipamentos")]
public class EquipmentController : ControllerBase
{
    private readonly IEquipmentService _equipmentService;

    public EquipmentController(IEquipmentService equipmentService)
    {
        _equipmentService = equipmentService;
    }

    [HttpGet("posicao/{id:guid}")]
    public async Task<ActionResult<EquipmentResponse>> GetMostRecentEquipment(Guid id)
    {
        return await _equipmentService.GetMostRecentEquipmentPosition(id);
    }

    [HttpGet("{id:guid}", Name = "GetEquipmentById")]
    public async Task<ActionResult<EquipmentResponse?>> GetEquipmentById(Guid id)
    {
        return await _equipmentService.GetEquipmentByIdAsync(id);
    }

    [HttpPost]
    public async Task<ActionResult<EquipmentResponse>> CreateEquipment(CreateEquipmentRequest equipmentRequest)
    {
        var equipment = await _equipmentService.CreateEquipmentAsync(equipmentRequest);

        return new CreatedAtRouteResult(nameof(GetEquipmentById), new { id = equipment.Id }, equipment);
    }

    [HttpPut("{id:guid}")]
    public async Task<ActionResult<EquipmentResponse>> EditEquipment(Guid id, EditEquipmentRequest editEquipmentRequest)
    {
        var editedEquipment = await _equipmentService.EditEquipmentAsync(editEquipmentRequest, id);

        return Ok(editedEquipment);
    }

    [HttpDelete("{id:guid}")]
    public async Task<ActionResult> DeleteEquipment(Guid id)
    {
        await _equipmentService.DeleteEquipmentAsync(id);

        return Ok();
    }
}
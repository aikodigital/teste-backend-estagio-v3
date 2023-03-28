
using Microsoft.AspNetCore.Mvc;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStates;

namespace TesteTecnico.Application.Controllers;

[ApiController]
[Route("/api/estado-equipamentos")]
public class EquipmentStateController : ControllerBase
{
    private readonly IEquipmentStateService _equipmentStateService;

    public EquipmentStateController(IEquipmentStateService equipmentStateService)
    {
        _equipmentStateService = equipmentStateService;
    }

    [HttpGet("equipamento/{id:guid}")]
    public async Task<ActionResult<EquipmentStateResponse>> GetMostRecentEquipmentState(Guid id)
    {
        return await _equipmentStateService.GetMostRecentStateOfEquipment(id);
    }

    [HttpGet("{id:guid}", Name = "GetEquipmentStateById")]
    public async Task<ActionResult<EquipmentStateResponse>> GetEquipmentStateById(Guid id)
    {
        return await _equipmentStateService.GetEquipmentStateByIdAsync(id);
    }

    [HttpPost]
    public async Task<ActionResult<EquipmentStateResponse>> CreateEquipmentState(CreateEquipmentState createEquipmentState)
    {
        var equipmentState = await _equipmentStateService.CreateEquipmentStateAsync(createEquipmentState);

        return new CreatedAtRouteResult(nameof(GetEquipmentStateById), new { id = equipmentState.Id }, equipmentState);
    }

    [HttpPut("{id:guid}")]
    public async Task<ActionResult<EquipmentStateResponse>> EditEquipmentState(Guid id, EditEquipmentState editEquipmentState)
    {
        var equipmentState = await _equipmentStateService.EditEquipmentStateAsync(editEquipmentState, id);
        return Ok(equipmentState);
    }

    [HttpDelete("{id:guid}")]
    public async Task<ActionResult> DeleteEquipmentState(Guid id)
    {
        await _equipmentStateService.DeleteEquipmentStateAsync(id);

        return Ok();
    }
}
using Microsoft.AspNetCore.Mvc;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStateHistories;

namespace TesteTecnico.Application.Controllers;

[ApiController]
[Route("/api/historico-estado-equipamentos")]
public class EquipmentStateHistoryController : ControllerBase
{
    private readonly IEquipmentStateHistoryService _equipmentStateHistoryService;

    public EquipmentStateHistoryController(IEquipmentStateHistoryService equipmentStateHistoryService)
    {
        _equipmentStateHistoryService = equipmentStateHistoryService;
    }

    [HttpGet("{id:guid}", Name = "GetEquipmentStateHistoryById")]
    public async Task<ActionResult<EquipmentStateHistoryResponse>> GetEquipmentStateHistoryById(Guid id)
    {
        return await _equipmentStateHistoryService.GetEquipmentStateHistoryByIdAsync(id);
    }

    [HttpPost]
    public async Task<ActionResult<EquipmentStateHistoryResponse>> CreateEquipmentStateHistory(CreateEquipmentStateHistory createEquipmentStateHistory)
    {
        var createdEquipmentStateHistory = await _equipmentStateHistoryService.CreateEquipmentStateHistoryAsync(createEquipmentStateHistory);

        return new CreatedAtRouteResult(nameof(GetEquipmentStateHistoryById), new { id = createdEquipmentStateHistory.Id }, createdEquipmentStateHistory);
    }

    [HttpPut("{id:guid}")]
    public async Task<ActionResult<EquipmentStateHistoryResponse>> EditEquipmentStateHistory(Guid id, EditEquipmentStateHistory editEquipmentStateHistory)
    {
        var editedEquipmentStateHistory = await _equipmentStateHistoryService.EditEquipmentStateHistoryAsync(editEquipmentStateHistory, id);

        return Ok(editedEquipmentStateHistory);
    }

    [HttpDelete("{id:guid}")]
    public async Task<ActionResult> DeleteEquipmentStateHistory(Guid id)
    {
        await _equipmentStateHistoryService.DeleteEquipmentStateHistoryAsync(id);

        return Ok();
    }
}
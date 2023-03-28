using Microsoft.AspNetCore.Mvc;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;

namespace TesteTecnico.Application.Controllers;

[ApiController]
[Route("/api/posicao-equipamentos")]
public class EquipmentPositionHistoryController : ControllerBase
{
    private readonly IEquipmentPositionHistoryService _equipmentPositionHistoryService;

    public EquipmentPositionHistoryController(IEquipmentPositionHistoryService equipmentPositionHistoryService)
    {
        _equipmentPositionHistoryService = equipmentPositionHistoryService;
    }

    [HttpGet("{id:guid}", Name = "GetEquipmentPositionHistoryById")]
    public async Task<ActionResult<EquipmentPositionHistoryResponse>> GetEquipmentPositionHistoryById(Guid id)
    {
        return await _equipmentPositionHistoryService.GetEquipmentPositionHistoryByIdAsync(id);
    }

    [HttpPost]
    public async Task<ActionResult<EquipmentPositionHistoryResponse>> CreateEquipmentPositionHistory(CreateEquipmentPositionHistory createEquipmentPositionHistory)
    {
        EquipmentPositionHistoryResponse equipmentPositionHistory = await _equipmentPositionHistoryService.CreateEquipmentPositionHistoryAsync(createEquipmentPositionHistory);

        return new CreatedAtRouteResult(nameof(GetEquipmentPositionHistoryById), new { id = equipmentPositionHistory.Id }, equipmentPositionHistory);
    }

    [HttpPut("{id:guid}")]
    public async Task<ActionResult<EquipmentPositionHistoryResponse>> EditEquipmentPositionHistory(Guid id, EditEquipmentPositionHistory editEquipmentPositionHistory)
    {
        EquipmentPositionHistoryResponse equipmentPositionHistory = await _equipmentPositionHistoryService.EditEquipmentPositionHistoryAsync(editEquipmentPositionHistory, id);

        return Ok(equipmentPositionHistory);
    }

    [HttpDelete("{id:guid}")]
    public async Task<ActionResult> DeleteEquipmentPositionHistory(Guid id)
    {
        await _equipmentPositionHistoryService.DeleteEquipmentPositionHistoryAsync(id);

        return Ok();
    }
}
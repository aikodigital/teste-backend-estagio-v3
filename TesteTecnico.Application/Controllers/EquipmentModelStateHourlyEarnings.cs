using Microsoft.AspNetCore.Mvc;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModelStateHourlyEarnings;

namespace TesteTecnico.Application.Controllers;

[ApiController]
[Route("/api/equipamento-valor-hora")]
public class EquipmentModelStateHourlyEarnings : ControllerBase
{
    private readonly IEquipmentModelStateHourlyEarningService _equipmentModelStateHourlyEarningService;

    public EquipmentModelStateHourlyEarnings(IEquipmentModelStateHourlyEarningService equipmentModelStateHourlyEarningService)
    {
        _equipmentModelStateHourlyEarningService = equipmentModelStateHourlyEarningService;
    }

    [HttpGet("{id:guid}", Name = "GetEquipmentModelStateHourlyEarningById")]
    public async Task<ActionResult<EquipmentModelStateHourlyEarningResponse>> GetEquipmentModelStateHourlyEarningById(Guid id)
    {
        return await _equipmentModelStateHourlyEarningService.GetEquipmentModelStateHourlyEarningByIdAsync(id);
    }

    [HttpPost]
    public async Task<ActionResult<EquipmentModelStateHourlyEarningResponse>> CreateEquipmentModelStateHourlyEarning(
        CreateEquipmentModelStateHourlyEarning equipmentModelState)
    {
        var equipmentModelStateHourlyEarning =
            await _equipmentModelStateHourlyEarningService.CreateEquipmentModelStateHourlyEarningAsync(equipmentModelState);

        return new CreatedAtRouteResult(
            nameof(GetEquipmentModelStateHourlyEarningById),
            new { id = equipmentModelStateHourlyEarning.Id },
            equipmentModelStateHourlyEarning);
    }

    [HttpPut("{id:guid}")]
    public async Task<ActionResult<EquipmentModelStateHourlyEarningResponse>> EditEquipmentModelStateHourlyEarning(
        Guid id, EditEquipmentModelStateHourlyEarning equipmentModelState
    )
    {
        var equipmentModelStateHourlyEarning =
            await _equipmentModelStateHourlyEarningService.EditEquipmentModelStateHourlyEarningAsync(equipmentModelState, id);

        return Ok(equipmentModelStateHourlyEarning);
    }

    [HttpDelete("{id:guid}")]
    public async Task<ActionResult> DeleteEquipmentModelStateHourlyEarning(Guid id)
    {
        await _equipmentModelStateHourlyEarningService.DeleteEquipmentModelStateHourlyEarningAsync(id);

        return Ok();
    }
}
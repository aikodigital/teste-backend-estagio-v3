using Microsoft.AspNetCore.Mvc;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;

namespace TesteTecnico.Application.Controllers;

[ApiController]
[Route("/api/modelos-equipamentos")]
public class EquipmentModelController : ControllerBase
{
    private readonly IEquipmentModelService _equipmentModelService;

    public EquipmentModelController(IEquipmentModelService equipmentModelService)
    {
        _equipmentModelService = equipmentModelService;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<EquipmentModelResponse>>> GetAllEquipmentModels()
    {
        var equipmentModels = await _equipmentModelService.GetAllEquipmentModelAsync();
        return Ok(equipmentModels);
    }

    [HttpGet("{id:guid}", Name = "GetEquipmentModelById")]
    public async Task<ActionResult<EquipmentModelResponse?>> GetEquipmentModelById(Guid id)
    {
        return await _equipmentModelService.GetEquipmentModelByIdAsync(id);
    }

    [HttpPost]
    public async Task<ActionResult<EquipmentModelResponse>> CreateEquipmentModel(CreateEquipmentModel equipmentModel)
    {
        var createdEquipmentModel = await _equipmentModelService.CreateEquipmentModelAsync(equipmentModel);

        return new CreatedAtRouteResult(nameof(GetEquipmentModelById), new { id = createdEquipmentModel.Id }, createdEquipmentModel);
    }

    [HttpPut("{id:guid}")]
    public async Task<ActionResult<EquipmentModelResponse>> EditEquipmentModel(Guid id, EditEquipmentModel equipmentModel)
    {
        var editedEquipmentModel = await _equipmentModelService.EditEquipmentModelAsync(equipmentModel, id);
        return Ok(editedEquipmentModel);
    }

    [HttpDelete("{id:guid}")]
    public async Task<ActionResult> DeleteEquipmentModel(Guid id)
    {
        await _equipmentModelService.DeleteEquipmentModelAsync(id);
        return Ok();
    }
}
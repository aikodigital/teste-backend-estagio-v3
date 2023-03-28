using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;
using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;

namespace EquipmentApi.Controllers
{
    [Route("api/equipment-state")]
    [ApiController]
    public class EquipmentStateController : ControllerBase
    {
        private readonly IEquipmentStateService _equipmentStateService;

        public EquipmentStateController(IEquipmentStateService equipmentStateService)
        {
            _equipmentStateService = equipmentStateService;
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(List<EquipmentState>))]
        public List<EquipmentState> GetEquipmentState()
        {
            return _equipmentStateService.GetEquipmentState();
        }

        [HttpGet("{id}")]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(EquipmentState))]
        public EquipmentState GetEquipmentById(Guid id)
        {
            return _equipmentStateService.GetEquipmentStateById(id);
        }

        [HttpPost]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string AddNewEquipmentState(EquipmentStateRequest equipmentRequest)
        {
            return _equipmentStateService.AddNewEquipmentState(equipmentRequest);
        }

        [HttpPut]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string UpdateEquipmentState(EquipmentStateRequest equipmentRequest, Guid id)
        {
            return _equipmentStateService.UpdateEquipmentState(equipmentRequest, id);
        }

        [HttpDelete]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string DeleteEquipmentState(Guid id)
        {
            return _equipmentStateService.DeleteEquipmentState(id);
        }
    }
}

using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;
using EquipmentDomain.Services;
using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;

namespace EquipmentApi.Controllers
{
    [Route("api/equipment-model-state-hourly-earnings")]
    [ApiController]
    public class EquipmentModelStateHourlyEarningController : ControllerBase
    {
        private readonly IEquipmentModelStateHourlyEarningService _equipmentModelStateHourlyEarningService;

        public EquipmentModelStateHourlyEarningController(IEquipmentModelStateHourlyEarningService equipmentModelStateHourlyEarningService)
        {
            _equipmentModelStateHourlyEarningService = equipmentModelStateHourlyEarningService;
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(List<EquipmentModelStateHourlyEarning>))]
        public List<EquipmentModelStateHourlyEarning> GetEquipment()
        {
            return _equipmentModelStateHourlyEarningService.GetEquipmentModelStateHourlyEarning();
        }

        [HttpPost]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string AddNewEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarningRequest equipmentRequest)
        {
            return _equipmentModelStateHourlyEarningService.AddNewEquipmentModelStateHourlyEarning(equipmentRequest);
        }

        [HttpPut]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string UpdateEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarningRequest storedEquipment, float value, Guid equipmentModelId, Guid equipmentStateId)
        {
            return _equipmentModelStateHourlyEarningService.UpdateEquipmentModelStateHourlyEarning(storedEquipment, value, equipmentModelId, equipmentStateId);
        }

        [HttpDelete]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string DeleteEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarningRequest storedEquipment)
        {
            return _equipmentModelStateHourlyEarningService.DeleteEquipmentModelStateHourlyEarning(storedEquipment);
        }
    }
}
 
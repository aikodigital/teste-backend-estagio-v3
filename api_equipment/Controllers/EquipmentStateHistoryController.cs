using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;
using EquipmentDomain.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;

namespace EquipmentApi.Controllers
{
    [Route("api/equipment_state_history")]
    [ApiController]
    public class EquipmentStateHistoryController : ControllerBase
    {
        private readonly IEquipmentStateHistoryService _equipmentStateHistoryService;

        public EquipmentStateHistoryController(IEquipmentStateHistoryService equipmentStateHistoryService)
        {
            _equipmentStateHistoryService = equipmentStateHistoryService;
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(List<EquipmentStateHistory>))]
        public List<EquipmentStateHistory> GetEquipmentStateHistory()
        {
            return _equipmentStateHistoryService.GetEquipmentStateHistory();
        }

        [HttpPost]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string AddNewEquipmentStateHistory(EquipmentStateHistoryRequest request)
        {
            return _equipmentStateHistoryService.AddNewEquipmentStateHistory(request);
        }

        [HttpPut]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string UpdateEquipmentStateHistory(EquipmentStateHistoryRequest storedEquipment, Guid equipmentId, Guid equipmentStateId, DateTime date)
        {
            return _equipmentStateHistoryService.UpdateEquipmentStateHistory(storedEquipment, equipmentId, equipmentStateId, date);
        }

        [HttpDelete]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string DeleteEquipmentStateHistory(EquipmentStateHistoryRequest storedEquipment)
        {
            return _equipmentStateHistoryService.DeleteEquipmentStateHistory(storedEquipment);
        }
    }
}

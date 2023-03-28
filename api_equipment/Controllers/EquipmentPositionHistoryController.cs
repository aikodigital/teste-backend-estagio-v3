using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;
using EquipmentDomain.Services;
using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;

namespace EquipmentApi.Controllers
{
    [Route("api/equipment_position_history")]
    [ApiController]
    public class EquipmentPositionHistoryController : ControllerBase
    {
        private readonly IEquipmentPositionHistoryService _equipmentPositionHistory;

        public EquipmentPositionHistoryController(IEquipmentPositionHistoryService equipmentPositionHistory)
        {
            _equipmentPositionHistory = equipmentPositionHistory;
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(List<EquipmentPositionHistory>))]
        public List<EquipmentPositionHistory> GetEquipmentPositionHistory()
        {
            return _equipmentPositionHistory.GetEquipmentPositionHistory();
        }

        [HttpPost]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string AddNewEquipmentPositionHistory(EquipmentPositionHistoryRequest request)
        {
            return _equipmentPositionHistory.AddNewEquipmentPositionHistory(request);
        }

        [HttpPut]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string UpdateEquipmentPositionHistory(EquipmentPositionHistoryDataRequest storedEquipment, float latitude, float longitude, Guid equipmentId)
        {
            return _equipmentPositionHistory.UpdateEquipmentPositionHistory(storedEquipment, latitude, longitude, equipmentId);
        }

        [HttpDelete]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string DeleteEquipmentPositionHistory(EquipmentPositionHistoryDataRequest storedEquipment)
        {
            return _equipmentPositionHistory.DeleteEquipmentPositionHistory(storedEquipment);
        }
    }
}

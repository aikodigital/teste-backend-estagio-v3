using Dapper;
using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Services;
using Microsoft.AspNetCore.Mvc;
using Npgsql;
using Swashbuckle.AspNetCore.Annotations;

namespace EquipmentApi.Controllers
{
    [Route("api/equipment-actual-position")]
    [ApiController]
    public class EquipmentActualPosition : ControllerBase
    {
        private readonly IEquipmentQueriesService _equipmentQueriesService;

        public EquipmentActualPosition(IEquipmentQueriesService equipmentQueriesService)
        {
            _equipmentQueriesService = equipmentQueriesService;
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(IEnumerable<EquipmentActualPositionResponse>))]
        public IEnumerable<EquipmentActualPositionResponse> GetEquipment()
        {
            return _equipmentQueriesService.GetActualPosition();
        }
    }
}

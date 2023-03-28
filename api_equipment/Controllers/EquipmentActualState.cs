using EquipmentDomain.DTOs;
using Microsoft.AspNetCore.Mvc;
using Npgsql;
using Dapper;
using Swashbuckle.AspNetCore.Annotations;
using EquipmentDomain.Interfaces.Services;

namespace EquipmentApi.Controllers
{
    [Route("api/equipment-actual-state")]
    [ApiController]
    public class EquipmentActualState : ControllerBase
    {
        private readonly IEquipmentQueriesService _equipmentQueriesService;

        public EquipmentActualState(IEquipmentQueriesService equipmentQueriesService)
        {
            _equipmentQueriesService = equipmentQueriesService;
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(IEnumerable<EquipmentActualStateResponse>))]
        public IEnumerable<EquipmentActualStateResponse> GetEquipment()
        {
            return _equipmentQueriesService.GetActualState();
        }

    }
}

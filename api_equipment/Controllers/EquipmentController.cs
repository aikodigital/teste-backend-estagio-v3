using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;

namespace api_equipment.Controllers
{
    [Route("api/equipment/")]
    [ApiController]
    public class EquipmentController : ControllerBase
    {
        private readonly IEquipmentService _equipmentService;

        public EquipmentController(IEquipmentService equipmentService)
        {
            _equipmentService = equipmentService;
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(List<Equipment>))]
        public List<Equipment> GetEquipment()
        {
            return _equipmentService.GetEquipment();
        }

        [HttpGet("{id}")]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(Equipment))]
        public Equipment GetEquipmentById(Guid id)
        {
            return _equipmentService.GetEquipmentById(id);
        }

        [HttpPost]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string AddNewEquipment(EquipmentRequest equipmentRequest)
        {
            return _equipmentService.AddNewEquipment(equipmentRequest);
        }

        [HttpPut]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string UpdateEquipment(EquipmentRequest equipmentRequest, Guid id)
        {
            return _equipmentService.UpdateEquipment(equipmentRequest, id);
        }

        [HttpDelete]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string DeleteEquipment(Guid id)
        {
            return _equipmentService.DeleteEquipment(id);
        }
    }
}

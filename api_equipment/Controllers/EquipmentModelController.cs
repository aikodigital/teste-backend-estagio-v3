using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;
using EquipmentDomain.Services;
using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;

namespace EquipmentApi.Controllers
{
    [Route("api/equipment-model")]
    [ApiController]
    public class EquipmentModelController : ControllerBase
    {
        private readonly IEquipmenteModelService _equipmentModelService;

        public EquipmentModelController(IEquipmenteModelService equipmentModelService)
        {
            _equipmentModelService = equipmentModelService;
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(List<EquipmentModel>))]
        public List<EquipmentModel> GetEquipmentState()
        {
            return _equipmentModelService.GetEquipmentModel();
        }

        [HttpGet("{id}")]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(EquipmentModel))]
        public EquipmentModel GetEquipmentStateById(Guid id)
        {
            return _equipmentModelService.GetEquipmentModelById(id);
        }

        [HttpPost]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string AddNewEquipmentState(EquipmentModelRequest equipmentRequest)
        {
            return _equipmentModelService.AddNewEquipmentModel(equipmentRequest);
        }

        [HttpPut]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string UpdateEquipmentState(EquipmentModelRequest equipmentRequest, Guid id)
        {
            return _equipmentModelService.UpdateEquipmentModel(equipmentRequest, id);
        }

        [HttpDelete]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(string))]
        public string DeleteEquipmentState(Guid id)
        {
            return _equipmentModelService.DeleteEquipmentModel(id);
        }
    }
}

using EquipmentDomain.DTOs;
using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Services
{
    public interface IEquipmenteModelService
    {
        public List<EquipmentModel> GetEquipmentModel();
        public EquipmentModel GetEquipmentModelById(Guid id);
        public string AddNewEquipmentModel(EquipmentModelRequest equipmentRequest);
        public string UpdateEquipmentModel(EquipmentModelRequest equipmentRequest, Guid id);
        public string DeleteEquipmentModel(Guid id);
    }
}

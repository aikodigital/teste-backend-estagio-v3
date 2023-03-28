using EquipmentDomain.DTOs;
using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Services
{
    public interface IEquipmentStateService
    {
        public List<EquipmentState> GetEquipmentState();
        public EquipmentState GetEquipmentStateById(Guid id);
        public string AddNewEquipmentState(EquipmentStateRequest equipmentRequest);
        public string UpdateEquipmentState(EquipmentStateRequest equipmentRequest, Guid id);
        public string DeleteEquipmentState(Guid id);
    }
}

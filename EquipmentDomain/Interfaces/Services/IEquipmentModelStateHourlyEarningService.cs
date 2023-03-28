using EquipmentDomain.DTOs;
using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Services
{
    public interface IEquipmentModelStateHourlyEarningService
    {
        public List<EquipmentModelStateHourlyEarning> GetEquipmentModelStateHourlyEarning();
        public string AddNewEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarningRequest equipmentRequest);
        public string UpdateEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarningRequest storedEquipment, float value, Guid equipmentModelId, Guid equipmentStateId);
        public string DeleteEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarningRequest storedEquipment);
    }
}

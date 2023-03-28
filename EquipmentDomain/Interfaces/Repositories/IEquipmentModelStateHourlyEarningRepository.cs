using EquipmentDomain.DTOs;
using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Repositories
{
    public interface IEquipmentModelStateHourlyEarningRepository
    {
        public List<EquipmentModelStateHourlyEarning> GetEquipmentModelStateHourlyEarning();
        public string AddNewEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarning newEquipment);
        public string UpdateEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarning storedEquipment, float value, Guid equipmentModelId, Guid equipmentStateId);
        public string DeleteEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarning storedEquipment);
    }
}

using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Repositories
{
    public interface IEquipmentStateHistoryRepository
    {
        public List<EquipmentStateHistory> GetEquipmentStateHistory();
        public string AddNewEquipmentStateHistory(EquipmentStateHistory newEquipment);
        public string UpdateEquipmentStateHistory(EquipmentStateHistory equipment, Guid equipmentId, Guid equipmentStateId, DateTime date);
        public string DeleteEquipmentStateHistory(EquipmentStateHistory storedEquipment);
    }
}

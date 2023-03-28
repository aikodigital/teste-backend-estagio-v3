using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Repositories
{
    public interface IEquipmentPositionHistoryRepository
    {
        public List<EquipmentPositionHistory> GetEquipmentPositionHistory();
        public string AddNewEquipmentPositionHistory(EquipmentPositionHistory newEquipment);
        public string UpdateEquipmentPositionHistory(EquipmentPositionHistory equipment, float lat, float lon, Guid equipmentId);
        public string DeleteEquipmentPositionHistory(EquipmentPositionHistory storedEquipment);
    }
}

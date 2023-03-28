using EquipmentDomain.DTOs;
using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Services
{
    public interface IEquipmentPositionHistoryService
    {
        public List<EquipmentPositionHistory> GetEquipmentPositionHistory();
        public string AddNewEquipmentPositionHistory(EquipmentPositionHistoryRequest request);
        public string UpdateEquipmentPositionHistory(EquipmentPositionHistoryDataRequest request, float lat, float lon, Guid equipmentId);
        public string DeleteEquipmentPositionHistory(EquipmentPositionHistoryDataRequest request);
    }
}

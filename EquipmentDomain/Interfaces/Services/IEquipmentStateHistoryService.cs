using EquipmentDomain.DTOs;
using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Services
{
    public interface IEquipmentStateHistoryService
    {
        public List<EquipmentStateHistory> GetEquipmentStateHistory();
        public string AddNewEquipmentStateHistory(EquipmentStateHistoryRequest request);
        public string UpdateEquipmentStateHistory(EquipmentStateHistoryRequest request, Guid equipmentId, Guid equipmentStateId, DateTime date);
        public string DeleteEquipmentStateHistory(EquipmentStateHistoryRequest request);
    }
}

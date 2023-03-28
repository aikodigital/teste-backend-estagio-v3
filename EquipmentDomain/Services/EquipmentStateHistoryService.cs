using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;

namespace EquipmentDomain.Services
{
    public class EquipmentStateHistoryService : IEquipmentStateHistoryService
    {
        private readonly IEquipmentStateHistoryRepository _equipment;

        public EquipmentStateHistoryService(IEquipmentStateHistoryRepository categoria)
        {
            _equipment = categoria;
        }

        public List<EquipmentStateHistory> GetEquipmentStateHistory()
        {
            List<EquipmentStateHistory> equipmentStateHistory = _equipment.GetEquipmentStateHistory();

            if (!equipmentStateHistory.Any())
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return equipmentStateHistory;

        }

        public string AddNewEquipmentStateHistory(EquipmentStateHistoryRequest request)
        {
            var equipmentStateHistory = new EquipmentStateHistory
            {
                EquipmentId = request.EquipmentId,
                Date = request.Date,
                EquipmentStateId = request.EquipmentStateId
            };

            return _equipment.AddNewEquipmentStateHistory(equipmentStateHistory);
        }

        public string UpdateEquipmentStateHistory(EquipmentStateHistoryRequest request, Guid equipmentId, Guid equipmentStateId, DateTime date)
        {
            var equipmentStateHistory = new EquipmentStateHistory
            {
                EquipmentId = request.EquipmentId,
                EquipmentStateId = request.EquipmentStateId,
                Date = request.Date
            };

            return _equipment.UpdateEquipmentStateHistory(equipmentStateHistory, equipmentId, equipmentStateId, date);
        }

        public string DeleteEquipmentStateHistory(EquipmentStateHistoryRequest request)
        {
            var equipmentStateHistory = new EquipmentStateHistory
            {
                EquipmentId = request.EquipmentId,
                EquipmentStateId = request.EquipmentStateId,
                Date = request.Date
            };

            return _equipment.DeleteEquipmentStateHistory(equipmentStateHistory);
        }
    }
}

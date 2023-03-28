using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;

namespace EquipmentDomain.Services
{
    public class EquipmentPositionHistoryService : IEquipmentPositionHistoryService
    {
        private readonly IEquipmentPositionHistoryRepository _equipmentPositionHistoryRepository;

        public EquipmentPositionHistoryService(IEquipmentPositionHistoryRepository equipmentPositionHistoryRepository)
        {
            _equipmentPositionHistoryRepository = equipmentPositionHistoryRepository;
        }

        public List<EquipmentPositionHistory> GetEquipmentPositionHistory()
        {
            List<EquipmentPositionHistory> equipmentPositionHistory = _equipmentPositionHistoryRepository.GetEquipmentPositionHistory();

            if (!equipmentPositionHistory.Any())
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return equipmentPositionHistory;
        }

        public string AddNewEquipmentPositionHistory(EquipmentPositionHistoryRequest request)
        {
            var equipmentPositionHistory = new EquipmentPositionHistory 
            {
                EquipmentId = request.EquipmentId,
                Date = DateTime.Now,
                Lat = request.Lat,
                Lon = request.Lon
            };

            return _equipmentPositionHistoryRepository.AddNewEquipmentPositionHistory(equipmentPositionHistory);
        }

        public string UpdateEquipmentPositionHistory(EquipmentPositionHistoryDataRequest request, float lat, float lon, Guid equipmentId)
        {
            var equipmentPositionHistory = new EquipmentPositionHistory
            {
                EquipmentId = request.EquipmentId,
                Date = request.Date,
                Lat = request.Lat,
                Lon = request.Lon
            };

            return _equipmentPositionHistoryRepository.UpdateEquipmentPositionHistory(equipmentPositionHistory, lat, lon, equipmentId);
        }

        public string DeleteEquipmentPositionHistory(EquipmentPositionHistoryDataRequest request)
        {
            var equipmentPositionHistory = new EquipmentPositionHistory
            {
                EquipmentId = request.EquipmentId,
                Date = request.Date,
                Lat = request.Lat,
                Lon = request.Lon
            };

            return _equipmentPositionHistoryRepository.DeleteEquipmentPositionHistory(equipmentPositionHistory);
        }
    }
}

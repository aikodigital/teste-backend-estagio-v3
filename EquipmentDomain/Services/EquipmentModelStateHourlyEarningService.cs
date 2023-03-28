using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;

namespace EquipmentDomain.Services
{
    public class EquipmentModelStateHourlyEarningService : IEquipmentModelStateHourlyEarningService
    {
        private readonly IEquipmentModelStateHourlyEarningRepository _equipmentModelStateHourlyEarningRepository;

        public EquipmentModelStateHourlyEarningService(IEquipmentModelStateHourlyEarningRepository equipmentModelStateHourlyEarningRepository)
        {
            _equipmentModelStateHourlyEarningRepository = equipmentModelStateHourlyEarningRepository;
        }

        public List<EquipmentModelStateHourlyEarning> GetEquipmentModelStateHourlyEarning()
        {
            List<EquipmentModelStateHourlyEarning> equipmentModelStateHourlyEarning = _equipmentModelStateHourlyEarningRepository.GetEquipmentModelStateHourlyEarning();

            if (!equipmentModelStateHourlyEarning.Any())
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return equipmentModelStateHourlyEarning;
        }

        public string AddNewEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarningRequest request)
        {
            var equipmentModelStateHourlyEarning = new EquipmentModelStateHourlyEarning
            {
                EquipmentModelId = request.EquipmentModelId,
                EquipmentStateId = request.EquipmentStateId,
                Value = request.Value
            };

            return _equipmentModelStateHourlyEarningRepository.AddNewEquipmentModelStateHourlyEarning(equipmentModelStateHourlyEarning);
        }

        public string UpdateEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarningRequest request, float value, Guid equipmentModelId, Guid equipmentStateId)
        {
            var equipmentModelStateHourlyEarning = new EquipmentModelStateHourlyEarning
            {
                EquipmentModelId = request.EquipmentModelId,
                EquipmentStateId = request.EquipmentStateId,
                Value = request.Value
            };

            return _equipmentModelStateHourlyEarningRepository.UpdateEquipmentModelStateHourlyEarning(equipmentModelStateHourlyEarning, value, equipmentModelId, equipmentStateId);
        }

        public string DeleteEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarningRequest request)
        {
            var equipmentModelStateHourlyEarning = new EquipmentModelStateHourlyEarning
            {
                EquipmentModelId = request.EquipmentModelId,
                EquipmentStateId = request.EquipmentStateId,
                Value = request.Value
            };

            return _equipmentModelStateHourlyEarningRepository.DeleteEquipmentModelStateHourlyEarning(equipmentModelStateHourlyEarning);
        }
    }
}

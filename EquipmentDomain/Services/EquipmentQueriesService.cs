using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Interfaces.Services;

namespace EquipmentDomain.Services
{
    public class EquipmentQueriesService : IEquipmentQueriesService
    {
        private readonly IEquipmentQueriesRespository _equipmentQueriesRespository;

        public EquipmentQueriesService(IEquipmentQueriesRespository equipmentQueriesRespository)
        {
            _equipmentQueriesRespository = equipmentQueriesRespository;
        }

        public IEnumerable<EquipmentActualPositionResponse> GetActualPosition()
        {
            return _equipmentQueriesRespository.GetActualPosition();
        }

        public IEnumerable<EquipmentActualStateResponse> GetActualState()
        {
            return _equipmentQueriesRespository.GetActualState();
        }
    }
}

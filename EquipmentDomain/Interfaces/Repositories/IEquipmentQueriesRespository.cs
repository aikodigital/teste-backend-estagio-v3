using EquipmentDomain.DTOs;

namespace EquipmentDomain.Interfaces.Repositories
{
    public interface IEquipmentQueriesRespository
    {
        public IEnumerable<EquipmentActualPositionResponse> GetActualPosition();
        public IEnumerable<EquipmentActualStateResponse> GetActualState();
    }
}

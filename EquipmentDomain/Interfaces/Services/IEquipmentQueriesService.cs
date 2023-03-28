using EquipmentDomain.DTOs;

namespace EquipmentDomain.Interfaces.Services
{
    public interface IEquipmentQueriesService
    {
        public IEnumerable<EquipmentActualPositionResponse> GetActualPosition();
        public IEnumerable<EquipmentActualStateResponse> GetActualState();
    }
}

using API_CRUD_Test.Models;

namespace API_CRUD_Test.Repository.Interfaces
{
    public interface IEquipmentStateHistory
    {
        List<EquipmentStateHistory> GetAllEquipmentStateHistory();
        EquipmentStateHistory Add(EquipmentStateHistory equipment);
        EquipmentStateHistory Edit(EquipmentStateHistory equipment);
        EquipmentStateHistory Remove(Guid equipId, DateTime date);
        EquipmentStateHistory CurrentState(Guid id);
    }
}

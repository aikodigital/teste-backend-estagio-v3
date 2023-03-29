using API_CRUD_Test.Models;

namespace API_CRUD_Test.Repository.Interfaces
{
    public interface IEquipmentPositionHistory
    {
        List<EquipmentPositionHistory> GetAllEquipmentPositionHistory();
        EquipmentPositionHistory Add(EquipmentPositionHistory equipment);
        EquipmentPositionHistory Edit(EquipmentPositionHistory equipment);
        EquipmentPositionHistory Remove(Guid equipId, DateTime date);
        EquipmentPositionHistory CurrentPosition(Guid id);
    }
}

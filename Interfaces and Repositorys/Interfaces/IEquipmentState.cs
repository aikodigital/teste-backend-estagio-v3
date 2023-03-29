using API_CRUD_Test.Models;

namespace API_CRUD_Test.Repository.Interfaces
{
    public interface IEquipmentState
    {
        EquipmentState Add(EquipmentState equipment);
        EquipmentState Remove(Guid id);
        EquipmentState Edit(EquipmentState equipment);
        List<EquipmentState> GetAllEquipmentState();
    }
}

using API_CRUD_Test.Models;

namespace API_CRUD_Test.Repository.Interfaces
{
    public interface IEquipment
    {
        Equipment Add(Equipment equipment);
        Equipment Remove(Guid id);
        Equipment Edit(Equipment equipment);
        List<Equipment> GetAllEquipment();
    }
}

using API_CRUD_Test.Models;

namespace API_CRUD_Test.Repository.Interfaces
{
    public interface IEquipmentModel
    {
        EquipmentModel Add(EquipmentModel equipment);
        EquipmentModel Remove(Guid id);
        List<EquipmentModel> GetAllEquipmentModel();
        EquipmentModel Edit(EquipmentModel equipment);
    }
}

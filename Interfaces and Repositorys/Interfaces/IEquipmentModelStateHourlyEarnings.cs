using API_CRUD_Test.Models;

namespace API_CRUD_Test.Repository.Interfaces
{
    public interface IEquipmentModelStateHourlyEarnings
    {
        List<EquipmentModelStateHourlyEarnings> GetAllEquipmentModelStateHourlyEarnings();
        EquipmentModelStateHourlyEarnings Add(EquipmentModelStateHourlyEarnings equipment);
        EquipmentModelStateHourlyEarnings Edit(EquipmentModelStateHourlyEarnings equipment);
        EquipmentModelStateHourlyEarnings Remove(Guid modelId, Guid stateId);
    }
}

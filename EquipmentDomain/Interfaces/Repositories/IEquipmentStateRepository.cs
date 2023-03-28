using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Repositories
{
    public interface IEquipmentStateRepository
    {
        public List<EquipmentState> GetEquipmentState();
        public EquipmentState GetEquipmentStateById(Guid id);
        public string AddNewEquipmentState(EquipmentState newEquipment);
        public string UpdateEquipmentState();
        public string DeleteEquipmentState(EquipmentState equipment);
    }
}

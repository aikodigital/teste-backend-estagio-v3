using EquipmentDomain.DTOs;
using EquipmentDomain.Models;

namespace EquipmentDomain.Interfaces.Repositories
{
    public interface IEquipmentModelRepository
    {
        public List<EquipmentModel> GetEquipmentModel();
        public EquipmentModel GetEquipmentModelById(Guid id);
        public string AddNewEquipmentModel(EquipmentModel newEquipment);
        public string UpdateEquipmentModel();
        public string DeleteEquipmentModel(EquipmentModel equipment);
    }
}

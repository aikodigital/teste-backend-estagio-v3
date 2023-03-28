using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;
using System.Xml.Linq;

namespace EquipmentDomain.Services
{
    public class EquipmentService : IEquipmentService
    {
        private readonly IEquipmentRepository _equipment;

        public EquipmentService(IEquipmentRepository categoria)
        {
            _equipment = categoria;
        }

        public List<Equipment> GetEquipment()
        {
            List<Equipment> equipment = _equipment.GetEquipment();

            if (!equipment.Any())
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return equipment;

        }

        public Equipment GetEquipmentById(Guid id)
        {
            Equipment equipment = _equipment.GetEquipmentById(id);

            if (equipment.Equals(null))
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return equipment;

        }

        public string AddNewEquipment(EquipmentRequest request)
        {
            var id = Guid.NewGuid();

            var equipment = new Equipment
            {
                Id = id,
                EquipmentModelId = request.EquipmentModelId,
                Name = request.Name
            };

            return _equipment.AddNewEquipment(equipment);
        }

        public string UpdateEquipment(EquipmentRequest request, Guid id)
        {
            Equipment equipment = _equipment.GetEquipmentById(id);

            if (equipment.Equals(null))
                throw new ArgumentException("Não existe nenhum registro no banco.");

            equipment.Name = request.Name;
            equipment.EquipmentModelId = request.EquipmentModelId;

            return _equipment.UpdateEquipment();
        }

        public string DeleteEquipment(Guid id)
        {
            Equipment equipment = _equipment.GetEquipmentById(id);

            if (equipment.Equals(null))
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return _equipment.DeleteEquipment(equipment);
        }
    }
}

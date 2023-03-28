using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;

namespace EquipmentDomain.Services
{
    public class EquipmenteModelService : IEquipmenteModelService
    {
        private readonly IEquipmentModelRepository _equipmentModelRepository;

        public EquipmenteModelService(IEquipmentModelRepository equipmentModelRepository)
        {
            _equipmentModelRepository = equipmentModelRepository;
        }

        public List<EquipmentModel> GetEquipmentModel()
        {
            List<EquipmentModel> equipmentModel = _equipmentModelRepository.GetEquipmentModel();

            if (!equipmentModel.Any())
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return equipmentModel;
        }

        public EquipmentModel GetEquipmentModelById(Guid id)
        {
            EquipmentModel equipment = _equipmentModelRepository.GetEquipmentModelById(id);

            if (equipment.Equals(null))
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return equipment;
        }

        public string AddNewEquipmentModel(EquipmentModelRequest request)
        {
            var id = Guid.NewGuid();

            var equipmentModel = new EquipmentModel
            {
                Id = id,
                Name = request.Name
            };

            return _equipmentModelRepository.AddNewEquipmentModel(equipmentModel);
        }

        public string UpdateEquipmentModel(EquipmentModelRequest request, Guid id)
        {
            EquipmentModel equipmentModel = _equipmentModelRepository.GetEquipmentModelById(id);

            if (equipmentModel.Equals(null))
                throw new ArgumentException("Não existe nenhum registro no banco.");

            equipmentModel.Name = request.Name;

            return _equipmentModelRepository.UpdateEquipmentModel();
        }

        public string DeleteEquipmentModel(Guid id)
        {
            EquipmentModel equipmentModel = _equipmentModelRepository.GetEquipmentModelById(id);

            if (equipmentModel.Equals(null))
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return _equipmentModelRepository.DeleteEquipmentModel(equipmentModel);
        }
    }
}

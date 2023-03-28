using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;

namespace EquipmentDomain.Services
{
    public class EquipmentStateService : IEquipmentStateService
    {
        private readonly IEquipmentStateRepository _equipmentState;

        public EquipmentStateService(IEquipmentStateRepository equipmentState)
        {
            _equipmentState = equipmentState;
        }

        public List<EquipmentState> GetEquipmentState()
        {
            List<EquipmentState> equipmentState = _equipmentState.GetEquipmentState();

            if (!equipmentState.Any())
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return equipmentState;

        }

        public EquipmentState GetEquipmentStateById(Guid id)
        {
            EquipmentState equipmentState = _equipmentState.GetEquipmentStateById(id);

            if (equipmentState.Equals(null))
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return equipmentState;

        }

        public string AddNewEquipmentState(EquipmentStateRequest request)
        {
            var id = Guid.NewGuid();

            var equipmentState = new EquipmentState
            {
                Id = id,
                Name = request.Name,
                Color = request.Color
            };

            return _equipmentState.AddNewEquipmentState(equipmentState);
        }

        public string UpdateEquipmentState(EquipmentStateRequest request, Guid id)
        {
            EquipmentState equipmentState = _equipmentState.GetEquipmentStateById(id);

            if (equipmentState.Equals(null))
                throw new ArgumentException("Não existe nenhum registro no banco.");

            equipmentState.Name = request.Name;
            equipmentState.Color = request.Color;

            return _equipmentState.UpdateEquipmentState();
        }

        public string DeleteEquipmentState(Guid id)
        {
            EquipmentState equipmentState = _equipmentState.GetEquipmentStateById(id);

            if (equipmentState.Equals(null))
                throw new ArgumentException("Não existe nenhum registro no banco.");

            return _equipmentState.DeleteEquipmentState(equipmentState);
        }
    }
}

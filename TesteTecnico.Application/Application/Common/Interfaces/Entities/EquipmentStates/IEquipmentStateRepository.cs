using TesteTecnico.Application.Application.Common.Interfaces.GenericRepository;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStates;

public interface IEquipmentStateRepository : IGenericRepository<EquipmentState>
{
    Task<EquipmentState?> GetEquipmentStateByIdAsync(Guid equipmentStateId);
}
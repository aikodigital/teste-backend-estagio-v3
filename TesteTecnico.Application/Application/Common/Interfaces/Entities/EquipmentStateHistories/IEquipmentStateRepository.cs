using TesteTecnico.Application.Application.Common.Interfaces.GenericRepository;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStateHistories;

public interface IEquipmentStateHistoryRepository : IGenericRepository<EquipmentStateHistory>
{
    Task<EquipmentStateHistory?> GetMostRecentStateFromEquipment(Guid equipmentStateId);
    Task<EquipmentStateHistory?> GetEquipmentStateHistoryById(Guid id, bool trackChanges = true);
}
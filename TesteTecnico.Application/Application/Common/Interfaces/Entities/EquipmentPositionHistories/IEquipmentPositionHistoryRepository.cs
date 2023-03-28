using TesteTecnico.Application.Application.Common.Interfaces.GenericRepository;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;

public interface IEquipmentPositionHistoryRepository : IGenericRepository<EquipmentPositionHistory>
{
    Task<EquipmentPositionHistory?> GetMostRecentEquipmentPosition(Guid equipmentId);
    Task<EquipmentPositionHistory?> GetEquipmentPositionHistoryByIdAsync(Guid id);
}

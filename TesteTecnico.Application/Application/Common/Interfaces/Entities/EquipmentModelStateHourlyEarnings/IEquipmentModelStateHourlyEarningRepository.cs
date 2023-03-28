using TesteTecnico.Application.Application.Common.Interfaces.GenericRepository;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModelStateHourlyEarnings;

public interface IEquipmentModelStateHourlyEarningRepository : IGenericRepository<EquipmentModelStateHourlyEarning>
{
    Task<EquipmentModelStateHourlyEarning?> GetEquipmentModelStateHourlyEarningByIdAsync(Guid id, bool trackChanges = true);
}
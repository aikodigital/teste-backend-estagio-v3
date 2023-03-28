using TesteTecnico.Application.Application.Common.Interfaces.GenericRepository;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;

public interface IEquipmentModelRepository : IGenericRepository<EquipmentModel>
{
    Task<EquipmentModel?> GetEquipmentModelByIdAsync(Guid id);
    Task<EquipmentModel?> GetEquipmentModelByNameAsync(string Name);
}
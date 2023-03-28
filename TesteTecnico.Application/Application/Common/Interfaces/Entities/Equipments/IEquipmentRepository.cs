using TesteTecnico.Application.Application.Common.Interfaces.GenericRepository;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;

public interface IEquipmentRepository : IGenericRepository<Equipment>
{
    Task<Equipment?> GetEquipmentByIdAsync(Guid id, bool trackChanges = true);
    Task<Equipment?> GetEquipmentByNameAsync(string name);
}
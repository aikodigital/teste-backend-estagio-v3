using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;
using TesteTecnico.Application.Domain.Entities;
using TesteTecnico.Application.Infrastructure.Persistence.DataContext;

namespace TesteTecnico.Application.Infrastructure.Persistence;

public class EquipmentRepository : GenericRepository<Equipment>, IEquipmentRepository
{
    private readonly AppDbContext _dbContext;
    public EquipmentRepository(AppDbContext dbContext) : base(dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<Equipment?> GetEquipmentByIdAsync(Guid id, bool trackChanges = true)
    {
        return trackChanges ?
        await _dbContext.Equipments
        .Include(equipment => equipment.EquipmentModel)
        .SingleOrDefaultAsync(equipment => equipment.Id == id)
        :
        await _dbContext.Equipments
        .Include(equipment => equipment.EquipmentModel)
        .AsNoTracking()
        .SingleOrDefaultAsync(equipment => equipment.Id == id);
    }

    public async Task<Equipment?> GetEquipmentByNameAsync(string name)
    {
        return await _dbContext.Equipments
        .Include(equipment => equipment.EquipmentModel)
        .SingleOrDefaultAsync(equipment => equipment.Name.ToLower() == name.ToLower());
    }
}
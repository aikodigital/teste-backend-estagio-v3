using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;
using TesteTecnico.Application.Domain.Entities;
using TesteTecnico.Application.Infrastructure.Persistence.DataContext;

namespace TesteTecnico.Application.Infrastructure.Persistence;

public class EquipmentModelRepository : GenericRepository<EquipmentModel>, IEquipmentModelRepository
{
    private readonly AppDbContext _dbContext;
    public EquipmentModelRepository(AppDbContext dbContext) : base(dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<EquipmentModel?> GetEquipmentModelByIdAsync(Guid id)
    {
        return await _dbContext.EquipmentModels.AsNoTracking().SingleOrDefaultAsync(model => model.Id == id);
    }

    public async Task<EquipmentModel?> GetEquipmentModelByNameAsync(string Name)
    {
        return await _dbContext.EquipmentModels.SingleOrDefaultAsync(model => model.Name.ToLower() == Name.ToLower());
    }
}
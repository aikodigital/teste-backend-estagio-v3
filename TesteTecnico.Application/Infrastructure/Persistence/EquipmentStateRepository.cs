using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStates;
using TesteTecnico.Application.Domain.Entities;
using TesteTecnico.Application.Infrastructure.Persistence.DataContext;

namespace TesteTecnico.Application.Infrastructure.Persistence;

public class EquipmentStateRepository : GenericRepository<EquipmentState>, IEquipmentStateRepository
{
    private readonly AppDbContext _dbContext;
    public EquipmentStateRepository(AppDbContext dbContext) : base(dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<EquipmentState?> GetEquipmentStateByIdAsync(Guid equipmentStateId)
    {
        return await _dbContext.EquipmentStates.AsNoTracking().SingleOrDefaultAsync(state => state.Id == equipmentStateId);
    }
}
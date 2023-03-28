using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStateHistories;
using TesteTecnico.Application.Domain.Entities;
using TesteTecnico.Application.Infrastructure.Persistence.DataContext;

namespace TesteTecnico.Application.Infrastructure.Persistence;

public class EquipmentStateHistoryRepository : GenericRepository<EquipmentStateHistory>, IEquipmentStateHistoryRepository
{
    private readonly AppDbContext _dbContext;
    public EquipmentStateHistoryRepository(AppDbContext dbContext) : base(dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<EquipmentStateHistory?> GetEquipmentStateHistoryById(Guid id, bool trackChanges = true)
    {
        return trackChanges ? await _dbContext.EquipmentStateHistories
        .Include(history => history.Equipment)
        .Include(history => history.EquipmentState)
        .AsNoTracking()
        .SingleOrDefaultAsync(history => history.Id == id)
        :
        await _dbContext.EquipmentStateHistories
        .Include(history => history.Equipment)
        .Include(history => history.EquipmentState)
        .SingleOrDefaultAsync(history => history.Id == id);
    }

    public async Task<EquipmentStateHistory?> GetMostRecentStateFromEquipment(Guid equipmentStateId)
    {
        return await _dbContext.EquipmentStateHistories
        .AsNoTracking()
        .Include(state => state.EquipmentState)
        .Where(state => state.EquipmentId == equipmentStateId)
        .OrderByDescending(state => state.Date)
        .FirstOrDefaultAsync();
    }
}

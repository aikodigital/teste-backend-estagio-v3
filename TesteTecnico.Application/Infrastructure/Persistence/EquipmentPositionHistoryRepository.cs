using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;
using TesteTecnico.Application.Domain.Entities;
using TesteTecnico.Application.Infrastructure.Persistence.DataContext;

namespace TesteTecnico.Application.Infrastructure.Persistence;

public class EquipmentPositionHistoryRepository : GenericRepository<EquipmentPositionHistory>, IEquipmentPositionHistoryRepository
{
    private readonly AppDbContext _dbContext;
    public EquipmentPositionHistoryRepository(AppDbContext dbContext) : base(dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<EquipmentPositionHistory?> GetEquipmentPositionHistoryByIdAsync(Guid id)
    {
        return await _dbContext.EquipmentPositionHistory
        .Include(position => position.Equipment)
            .ThenInclude(equipment => equipment.EquipmentModel)
        .AsNoTracking()
        .SingleOrDefaultAsync(position => position.Id == id);
    }

    public async Task<EquipmentPositionHistory?> GetMostRecentEquipmentPosition(Guid equipmentId)
    {
        return await _dbContext.EquipmentPositionHistory
        .AsNoTracking()
        .Include(position => position.Equipment)
            .ThenInclude(equipment => equipment.EquipmentModel)
        .Where(position => position.EquipmentId == equipmentId)
        .OrderByDescending(position => position.Date)
        .FirstOrDefaultAsync();
    }
}
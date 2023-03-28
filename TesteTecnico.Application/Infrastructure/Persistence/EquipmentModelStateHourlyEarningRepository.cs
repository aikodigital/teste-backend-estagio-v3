using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModelStateHourlyEarnings;
using TesteTecnico.Application.Domain.Entities;
using TesteTecnico.Application.Infrastructure.Persistence.DataContext;

namespace TesteTecnico.Application.Infrastructure.Persistence;

public class EquipmentModelStateHourlyEarningRepository : GenericRepository<EquipmentModelStateHourlyEarning>, IEquipmentModelStateHourlyEarningRepository
{
    private readonly AppDbContext _dbContext;
    public EquipmentModelStateHourlyEarningRepository(AppDbContext dbContext) : base(dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<EquipmentModelStateHourlyEarning?> GetEquipmentModelStateHourlyEarningByIdAsync(Guid id, bool trackChanges = true)
    {
        return trackChanges ? await _dbContext.EquipmentModelStateHourlyEarnings
        .Include(earning => earning.EquipmentModel)
        .Include(earning => earning.EquipmentState)
        .SingleOrDefaultAsync(earning => earning.Id == id)
        :
        await _dbContext.EquipmentModelStateHourlyEarnings
        .Include(earning => earning.EquipmentModel)
        .Include(earning => earning.EquipmentState)
        .AsNoTracking()
        .SingleOrDefaultAsync(earning => earning.Id == id);
    }
}
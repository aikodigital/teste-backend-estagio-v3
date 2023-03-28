using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Infrastructure.Persistence.DataContext;

public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

    public DbSet<Equipment> Equipments { get; set; } = null!;
    public DbSet<EquipmentModel> EquipmentModels { get; set; } = null!;
    public DbSet<EquipmentModelStateHourlyEarning> EquipmentModelStateHourlyEarnings { get; set; } = null!;
    public DbSet<EquipmentPositionHistory> EquipmentPositionHistory { get; set; } = null!;
    public DbSet<EquipmentState> EquipmentStates { get; set; } = null!;
    public DbSet<EquipmentStateHistory> EquipmentStateHistories { get; set; } = null!;
}
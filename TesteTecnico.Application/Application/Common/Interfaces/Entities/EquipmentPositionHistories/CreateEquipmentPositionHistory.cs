namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;

public record CreateEquipmentPositionHistory(DateOnly Date, Guid EquipmentId, decimal Lat, decimal Lon);
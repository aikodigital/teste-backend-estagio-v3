namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;

public record EditEquipmentPositionHistory(Guid Id, Guid EquipmentId, DateOnly Date, decimal Lat, decimal Lon);
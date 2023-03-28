namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStateHistories;

public record EditEquipmentStateHistory(Guid Id, Guid EquipmentId, Guid EquipmentStateId, DateOnly Date);
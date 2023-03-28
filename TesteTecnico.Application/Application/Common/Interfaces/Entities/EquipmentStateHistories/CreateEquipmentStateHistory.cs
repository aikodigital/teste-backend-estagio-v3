namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStateHistories;

public record CreateEquipmentStateHistory(Guid EquipmentId, Guid EquipmentStateId, DateOnly Date);
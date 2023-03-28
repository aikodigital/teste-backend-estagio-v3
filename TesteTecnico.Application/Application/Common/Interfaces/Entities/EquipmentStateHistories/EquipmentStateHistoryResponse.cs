using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStateHistories;

public record EquipmentStateHistoryResponse(Guid Id, Equipment Equipment, EquipmentState EquipmentState, DateOnly Date);
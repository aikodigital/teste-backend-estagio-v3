using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;

public record EquipmentPositionHistoryResponse(Guid Id, EquipmentResponse Equipment, DateOnly Date, decimal Lat, decimal Lon);
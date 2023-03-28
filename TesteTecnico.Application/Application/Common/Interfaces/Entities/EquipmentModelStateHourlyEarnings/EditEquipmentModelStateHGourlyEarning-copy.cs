namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModelStateHourlyEarnings;

public record EditEquipmentModelStateHourlyEarning(Guid Id, decimal Value, Guid EquipmentModelId, Guid EquipmentStateId);
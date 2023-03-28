namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;

public record EditEquipmentRequest(Guid Id, string Name, Guid EquipmentModelId);
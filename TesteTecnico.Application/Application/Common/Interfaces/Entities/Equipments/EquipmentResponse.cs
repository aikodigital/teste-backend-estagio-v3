using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;

public record EquipmentResponse(Guid Id, string Name, EquipmentModel EquipmentModel);
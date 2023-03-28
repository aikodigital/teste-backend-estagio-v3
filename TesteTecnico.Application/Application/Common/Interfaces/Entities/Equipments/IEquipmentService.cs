using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;

namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipamento;

public interface IEquipmentService
{
    Task<EquipmentResponse> GetEquipmentByIdAsync(Guid equipmentId);
    Task<EquipmentResponse> CreateEquipmentAsync(CreateEquipmentRequest equipmentRequest);
    Task<EquipmentResponse> EditEquipmentAsync(EditEquipmentRequest editEquipmentRequest, Guid routeId);
    Task DeleteEquipmentAsync(Guid equipmentId);
    Task<EquipmentResponse> GetMostRecentEquipmentPosition(Guid equipmentId);
}
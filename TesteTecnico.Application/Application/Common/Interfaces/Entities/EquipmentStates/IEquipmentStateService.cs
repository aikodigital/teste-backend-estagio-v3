namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStates;

public interface IEquipmentStateService
{
    Task<EquipmentStateResponse> GetMostRecentStateOfEquipment(Guid equipmentId);
    Task<EquipmentStateResponse> GetEquipmentStateByIdAsync(Guid equipmentStateId);
    Task<EquipmentStateResponse> CreateEquipmentStateAsync(CreateEquipmentState equipmentState);
    Task<EquipmentStateResponse> EditEquipmentStateAsync(EditEquipmentState editEquipmentState, Guid routeId);
    Task DeleteEquipmentStateAsync(Guid equipmentStateId);
}
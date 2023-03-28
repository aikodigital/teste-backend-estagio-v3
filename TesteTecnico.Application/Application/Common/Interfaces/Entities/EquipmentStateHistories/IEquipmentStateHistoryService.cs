namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStateHistories;

public interface IEquipmentStateHistoryService
{
    Task<EquipmentStateHistoryResponse> GetEquipmentStateHistoryByIdAsync(Guid equipmentId);
    Task<EquipmentStateHistoryResponse> CreateEquipmentStateHistoryAsync(CreateEquipmentStateHistory createEqupmentStateHistory);
    Task<EquipmentStateHistoryResponse> EditEquipmentStateHistoryAsync(EditEquipmentStateHistory editEquipmentStateHistory, Guid routeId);
    Task DeleteEquipmentStateHistoryAsync(Guid equipmentId);
}
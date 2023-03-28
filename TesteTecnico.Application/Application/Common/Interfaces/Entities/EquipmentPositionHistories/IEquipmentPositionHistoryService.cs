namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;

public interface IEquipmentPositionHistoryService
{
    Task<EquipmentPositionHistoryResponse> GetEquipmentPositionHistoryByIdAsync(Guid id);
    Task<EquipmentPositionHistoryResponse> CreateEquipmentPositionHistoryAsync(CreateEquipmentPositionHistory createEquipmentPositionHistory);
    Task<EquipmentPositionHistoryResponse> EditEquipmentPositionHistoryAsync(EditEquipmentPositionHistory editEquipmentPositionHistory, Guid routeId);
    Task DeleteEquipmentPositionHistoryAsync(Guid id);
}
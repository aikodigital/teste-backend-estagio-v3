namespace TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;

public interface IEquipmentModelService
{
    Task<IEnumerable<EquipmentModelResponse>> GetAllEquipmentModelAsync();
    Task<EquipmentModelResponse?> GetEquipmentModelByIdAsync(Guid id);
    Task<EquipmentModelResponse> CreateEquipmentModelAsync(CreateEquipmentModel equipmentModel);
    Task<EquipmentModelResponse> EditEquipmentModelAsync(EditEquipmentModel equipmentModel, Guid routeId);
    Task DeleteEquipmentModelAsync(Guid id);
}
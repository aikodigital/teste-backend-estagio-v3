using AutoMapper;
using TesteTecnico.Application.Application.Common.Exceptions;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Services.Entities;

public class EquipmentModelService : IEquipmentModelService
{
    private readonly IEquipmentModelRepository _equipmentModelRepository;
    private readonly IMapper _mapper;

    public EquipmentModelService(IEquipmentModelRepository equipmentModelRepository, IMapper mapper)
    {
        _equipmentModelRepository = equipmentModelRepository;
        _mapper = mapper;
    }

    public async Task<IEnumerable<EquipmentModelResponse>> GetAllEquipmentModelAsync()
    {
        var equipmentModels = await _equipmentModelRepository.GetAllAsync();
        return _mapper.Map<List<EquipmentModelResponse>>(equipmentModels);
    }

    public async Task<EquipmentModelResponse?> GetEquipmentModelByIdAsync(Guid id)
    {
        EquipmentModel? equipmentModel = await _equipmentModelRepository.GetByIdAsync(id);
        if (equipmentModel is null)
            throw new NotFoundException("Modelo de produto com o id especificado não existe.");

        return _mapper.Map<EquipmentModelResponse>(equipmentModel);
    }

    public async Task<EquipmentModelResponse> CreateEquipmentModelAsync(CreateEquipmentModel createEquipmentModel)
    {
        EquipmentModel? equipmentModel = await _equipmentModelRepository.GetEquipmentModelByNameAsync(createEquipmentModel.Name);
        if (equipmentModel is not null)
            throw new ConflictException("Modelo com o nome especificado já existe.");

        EquipmentModel equipmentModelToCreate = _mapper.Map<EquipmentModel>(createEquipmentModel);
        _equipmentModelRepository.Add(equipmentModelToCreate);
        await _equipmentModelRepository.CommitAsync();

        return _mapper.Map<EquipmentModelResponse>(equipmentModelToCreate);
    }

    public async Task<EquipmentModelResponse> EditEquipmentModelAsync(EditEquipmentModel editEquipmentModel, Guid routeId)
    {
        if (editEquipmentModel.Id != routeId)
            throw new BadRequestException("Id da rota não coincide com o id especificado.");

        EquipmentModel? equipmentModel = await _equipmentModelRepository.GetEquipmentModelByIdAsync(editEquipmentModel.Id);
        if (equipmentModel is null)
            throw new NotFoundException("Modelo com o id especificado não existe.");

        EquipmentModel editedEquipmentModel = _mapper.Map<EquipmentModel>(editEquipmentModel);
        _equipmentModelRepository.Update(editedEquipmentModel);
        await _equipmentModelRepository.CommitAsync();

        return _mapper.Map<EquipmentModelResponse>(editedEquipmentModel);
    }

    public async Task DeleteEquipmentModelAsync(Guid id)
    {
        EquipmentModel? equipmentModel = await _equipmentModelRepository.GetByIdAsync(id);
        if (equipmentModel is null)
            throw new NotFoundException("Modelo com o id especificado não existe.");

        _equipmentModelRepository.Delete(equipmentModel);
        await _equipmentModelRepository.CommitAsync();
    }
}
using AutoMapper;
using TesteTecnico.Application.Application.Common.Exceptions;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModelStateHourlyEarnings;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStates;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Services.Entities;

public class EquipmentModelStateHourlyEarningService : IEquipmentModelStateHourlyEarningService
{
    private readonly IEquipmentModelStateHourlyEarningRepository _equipmentModelStateHourlyEarningRepository;
    private readonly IEquipmentModelRepository _equipmentModelRepository;
    private readonly IEquipmentStateRepository _equipmentStateRepository;
    private readonly IMapper _mapper;

    public EquipmentModelStateHourlyEarningService(
        IEquipmentModelStateHourlyEarningRepository equipmentModelStateHourlyEarningRepository,
        IEquipmentModelRepository equipmentModelRepository,
        IEquipmentStateRepository equipmentStateRepository,
        IMapper mapper)
    {
        _equipmentModelStateHourlyEarningRepository = equipmentModelStateHourlyEarningRepository;
        _equipmentModelRepository = equipmentModelRepository;
        _equipmentStateRepository = equipmentStateRepository;
        _mapper = mapper;
    }

    public async Task<EquipmentModelStateHourlyEarningResponse> GetEquipmentModelStateHourlyEarningByIdAsync(Guid id)
    {
        EquipmentModelStateHourlyEarning? equipmentModelStateHourlyEarning = await _equipmentModelStateHourlyEarningRepository.GetEquipmentModelStateHourlyEarningByIdAsync(id, false);
        if (equipmentModelStateHourlyEarning is null)
            throw new NotFoundException("Valor por hora do modelo de equipamento com o id especificado não existe.");

        EquipmentModelResponse equipmentModelResponse = _mapper.Map<EquipmentModelResponse>(equipmentModelStateHourlyEarning.EquipmentModel);
        EquipmentStateResponse equipmentStateResponse = _mapper.Map<EquipmentStateResponse>(equipmentModelStateHourlyEarning.EquipmentState);

        return new EquipmentModelStateHourlyEarningResponse(
            Id: equipmentModelStateHourlyEarning.Id,
            Value: equipmentModelStateHourlyEarning.Value,
            EquipmentModel: equipmentModelResponse,
            EquipmentState: equipmentStateResponse
        );
    }

    public async Task<EquipmentModelStateHourlyEarningResponse> CreateEquipmentModelStateHourlyEarningAsync(CreateEquipmentModelStateHourlyEarning equipmentModelState)
    {
        EquipmentModel equipmentModel = await GetEquipmentModelAsync(equipmentModelState.EquipmentModelId);
        EquipmentState equipmentState = await GetEquipmentStateAsync(equipmentModelState.EquipmentStateId);

        EquipmentModelStateHourlyEarning equipmentModelStateToCreate = _mapper.Map<EquipmentModelStateHourlyEarning>(equipmentModelState);
        _equipmentModelStateHourlyEarningRepository.Add(equipmentModelStateToCreate);
        await _equipmentModelStateHourlyEarningRepository.CommitAsync();

        EquipmentModelResponse equipmentModelResponse = _mapper.Map<EquipmentModelResponse>(equipmentModel);
        EquipmentStateResponse equipmentStateResponse = _mapper.Map<EquipmentStateResponse>(equipmentState);

        return new EquipmentModelStateHourlyEarningResponse(
            Id: equipmentModelStateToCreate.Id,
            Value: equipmentModelStateToCreate.Value,
            EquipmentModel: equipmentModelResponse,
            equipmentStateResponse
        );
    }

    public async Task<EquipmentModelStateHourlyEarningResponse> EditEquipmentModelStateHourlyEarningAsync(
        EditEquipmentModelStateHourlyEarning equipmentModelState, Guid routeId)
    {
        if (equipmentModelState.Id != routeId)
            throw new BadRequestException("Rota não coincide com o id especificado.");

        EquipmentModelStateHourlyEarning? modelStateToUpdate =
            await _equipmentModelStateHourlyEarningRepository.GetEquipmentModelStateHourlyEarningByIdAsync(equipmentModelState.Id, false);

        if (modelStateToUpdate is null)
            throw new NotFoundException("Valor por hora do modelo de equipamento com o id especificado não existe.");

        EquipmentModelStateHourlyEarning equipmentModelStateHourlyEarning = _mapper.Map<EquipmentModelStateHourlyEarning>(equipmentModelState);
        _equipmentModelStateHourlyEarningRepository.Update(equipmentModelStateHourlyEarning);
        await _equipmentModelStateHourlyEarningRepository.CommitAsync();

        EquipmentModelResponse equipmentModelResponse = _mapper.Map<EquipmentModelResponse>(modelStateToUpdate.EquipmentModel);
        EquipmentStateResponse equipmentStateResponse = _mapper.Map<EquipmentStateResponse>(modelStateToUpdate.EquipmentState);

        return new EquipmentModelStateHourlyEarningResponse(
            Id: equipmentModelStateHourlyEarning.Id,
            Value: equipmentModelStateHourlyEarning.Value,
            EquipmentModel: equipmentModelResponse,
            EquipmentState: equipmentStateResponse
        );
    }

    public async Task DeleteEquipmentModelStateHourlyEarningAsync(Guid id)
    {
        EquipmentModelStateHourlyEarning? modelStateHourlyEarningToDelete = await _equipmentModelStateHourlyEarningRepository.GetByIdAsync(id);
        if (modelStateHourlyEarningToDelete is null)
            throw new NotFoundException("Valor por hora do modelo de equipamento com o id especificado não existe.");

        _equipmentModelStateHourlyEarningRepository.Delete(modelStateHourlyEarningToDelete);
        await _equipmentModelStateHourlyEarningRepository.CommitAsync();
    }

    private async Task<EquipmentState> GetEquipmentStateAsync(Guid equipmentStateId)
    {
        EquipmentState? equipmentState = await _equipmentStateRepository.GetEquipmentStateByIdAsync(equipmentStateId);
        if (equipmentState is null)
            throw new NotFoundException("Estado do equipamento com o id especificado não existe.");

        return equipmentState;
    }

    private async Task<EquipmentModel> GetEquipmentModelAsync(Guid equipmentModelId)
    {
        EquipmentModel? equipmentModel = await _equipmentModelRepository.GetEquipmentModelByIdAsync(equipmentModelId);
        if (equipmentModel is null)
            throw new NotFoundException("Modelo de equipamento com o id especificado não existe.");

        return equipmentModel;
    }
}
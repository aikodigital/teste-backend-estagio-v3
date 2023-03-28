using AutoMapper;
using TesteTecnico.Application.Application.Common.Exceptions;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStates;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Services.Entities;

public class EquipmentStateService : IEquipmentStateService
{
    private readonly IEquipmentStateRepository _equipmentStateRepository;
    private readonly IMapper _mapper;

    public EquipmentStateService(IEquipmentStateRepository equipmentStateRepository, IMapper mapper)
    {
        _equipmentStateRepository = equipmentStateRepository;
        _mapper = mapper;
    }

    public async Task<EquipmentStateResponse> GetEquipmentStateByIdAsync(Guid equipmentStateId)
    {
        EquipmentState? equipmentState = await _equipmentStateRepository.GetEquipmentStateByIdAsync(equipmentStateId);
        if (equipmentState is null)
            throw new NotFoundException("Estado do equipamento com o id especificado não foi encontrado.");

        return _mapper.Map<EquipmentStateResponse>(equipmentState);
    }

    public async Task<EquipmentStateResponse> CreateEquipmentStateAsync(CreateEquipmentState createEquipmentState)
    {
        EquipmentState equipmentState = _mapper.Map<EquipmentState>(createEquipmentState);
        _equipmentStateRepository.Add(equipmentState);
        await _equipmentStateRepository.CommitAsync();

        return _mapper.Map<EquipmentStateResponse>(equipmentState);
    }

    public async Task<EquipmentStateResponse> EditEquipmentStateAsync(EditEquipmentState editEquipmentState, Guid routeId)
    {
        if (editEquipmentState.Id != routeId)
            throw new BadRequestException("Rota não coincide com o id especificado.");

        EquipmentState? equipmentState = await _equipmentStateRepository.GetEquipmentStateByIdAsync(editEquipmentState.Id);
        if (equipmentState is null)
            throw new NotFoundException("Estado do equipamento com o id especificado não foi encontrado.");

        EquipmentState equipmentStateToEdit = _mapper.Map<EquipmentState>(editEquipmentState);
        _equipmentStateRepository.Update(equipmentStateToEdit);
        await _equipmentStateRepository.CommitAsync();

        return _mapper.Map<EquipmentStateResponse>(equipmentStateToEdit);
    }

    public async Task DeleteEquipmentStateAsync(Guid equipmentStateId)
    {
        EquipmentState? equipmentState = await _equipmentStateRepository.GetByIdAsync(equipmentStateId);
        if (equipmentState is null)
            throw new NotFoundException("Estado do equipamento com o id especificado não foi encontrado.");

        _equipmentStateRepository.Delete(equipmentState);
        await _equipmentStateRepository.CommitAsync();
    }
}
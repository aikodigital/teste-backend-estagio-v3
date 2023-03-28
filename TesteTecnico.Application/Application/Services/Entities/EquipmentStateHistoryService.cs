using AutoMapper;
using TesteTecnico.Application.Application.Common.Exceptions;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipamento;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStateHistories;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStates;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Services.Entities;

public class EquipmentStateHistoryService : IEquipmentStateHistoryService
{
    private readonly IEquipmentStateHistoryRepository _equipmentStateHistoryRepository;
    private readonly IEquipmentService _equipmentService;
    private readonly IEquipmentStateService _equipmentStateService;
    private readonly IMapper _mapper;

    public EquipmentStateHistoryService(
        IEquipmentStateHistoryRepository equipmentStateHistoryRepository,
        IEquipmentService equipmentService,
        IEquipmentStateService equipmentStateService,
        IMapper mapper)
    {
        _equipmentStateHistoryRepository = equipmentStateHistoryRepository;
        _equipmentService = equipmentService;
        _equipmentStateService = equipmentStateService;
        _mapper = mapper;
    }

    public async Task<EquipmentStateHistoryResponse> GetEquipmentStateHistoryByIdAsync(Guid equipmentId)
    {
        EquipmentStateHistory? equipmentStateHistory = await _equipmentStateHistoryRepository.GetEquipmentStateHistoryById(equipmentId, false);
        if (equipmentStateHistory is null)
            throw new NotFoundException("Histórico de estado de equipamento com o id especificado não existe.");

        return new EquipmentStateHistoryResponse(
            Id: equipmentStateHistory.Id,
            Equipment: equipmentStateHistory.Equipment,
            EquipmentState: equipmentStateHistory.EquipmentState,
            Date: equipmentStateHistory.Date
        );
    }

    public async Task<EquipmentStateHistoryResponse> CreateEquipmentStateHistoryAsync(CreateEquipmentStateHistory createEquipmentStateHistory)
    {
        await _equipmentService.GetEquipmentByIdAsync(createEquipmentStateHistory.EquipmentId);
        await _equipmentStateService.GetEquipmentStateByIdAsync(createEquipmentStateHistory.EquipmentStateId);

        DateOnly todaysDate = DateOnly.FromDateTime(DateTime.Today);
        if (createEquipmentStateHistory.Date > todaysDate)
            throw new BadRequestException("Data não pode ser no futuro.");

        EquipmentStateHistory equipmentStateHistoryToCreate = _mapper.Map<EquipmentStateHistory>(createEquipmentStateHistory);
        _equipmentStateHistoryRepository.Add(equipmentStateHistoryToCreate);
        await _equipmentStateHistoryRepository.CommitAsync();

        return new EquipmentStateHistoryResponse(
            Id: equipmentStateHistoryToCreate.Id,
            Equipment: equipmentStateHistoryToCreate.Equipment,
            EquipmentState: equipmentStateHistoryToCreate.EquipmentState,
            Date: equipmentStateHistoryToCreate.Date);
    }

    public async Task<EquipmentStateHistoryResponse> EditEquipmentStateHistoryAsync(EditEquipmentStateHistory editEquipmentStateHistory, Guid routeId)
    {
        if (editEquipmentStateHistory.Id != routeId)
            throw new BadRequestException("Rota não coincide com o id especificado.");

        EquipmentStateHistory? equipmentStateHistoryToEdit = await _equipmentStateHistoryRepository.GetEquipmentStateHistoryById(editEquipmentStateHistory.Id);
        if (equipmentStateHistoryToEdit is null)
            throw new NotFoundException("Histórico de estado de equipamento com o id especificado não existe.");

        await _equipmentService.GetEquipmentByIdAsync(editEquipmentStateHistory.EquipmentId);
        await _equipmentStateService.GetEquipmentStateByIdAsync(editEquipmentStateHistory.EquipmentStateId);

        EquipmentStateHistory editedEquipmentStateHistory = _mapper.Map<EquipmentStateHistory>(editEquipmentStateHistory);
        _equipmentStateHistoryRepository.Update(editedEquipmentStateHistory);
        await _equipmentStateHistoryRepository.CommitAsync();

        return new EquipmentStateHistoryResponse(
            Id: editedEquipmentStateHistory.Id,
            Equipment: editedEquipmentStateHistory.Equipment,
            EquipmentState: editedEquipmentStateHistory.EquipmentState,
            Date: editedEquipmentStateHistory.Date);
    }

    public async Task DeleteEquipmentStateHistoryAsync(Guid equipmentId)
    {
        EquipmentStateHistory? equipmentStateHistoryToDelete = await _equipmentStateHistoryRepository.GetByIdAsync(equipmentId);
        if (equipmentStateHistoryToDelete is null)
            throw new NotFoundException("Histórico de estado de equipamento com o id especificado não existe.");

        _equipmentStateHistoryRepository.Delete(equipmentStateHistoryToDelete);
        await _equipmentStateHistoryRepository.CommitAsync();
    }
}
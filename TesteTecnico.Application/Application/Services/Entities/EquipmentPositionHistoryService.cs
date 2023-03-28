using AutoMapper;
using TesteTecnico.Application.Application.Common.Exceptions;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipamento;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Services.Entities;

public class EquipmentPositionHistoryService : IEquipmentPositionHistoryService
{
    private readonly IEquipmentPositionHistoryRepository _equipmentPositionHistoryRepository;
    private readonly IEquipmentService _equipmentService;
    private readonly IMapper _mapper;

    public EquipmentPositionHistoryService(IEquipmentPositionHistoryRepository equipmentPositionHistoryRepository, IEquipmentService equipmentService, IMapper mapper)
    {
        _equipmentPositionHistoryRepository = equipmentPositionHistoryRepository;
        _equipmentService = equipmentService;
        _mapper = mapper;
    }

    public async Task<EquipmentPositionHistoryResponse> GetEquipmentPositionHistoryByIdAsync(Guid id)
    {
        EquipmentPositionHistory? equipmentPositionHistory = await _equipmentPositionHistoryRepository.GetEquipmentPositionHistoryByIdAsync(id);
        if (equipmentPositionHistory is null)
            throw new NotFoundException("Histórico de posição do equipamento com o id especificado não foi encontrado.");

        EquipmentResponse equipmentResponse = _mapper.Map<EquipmentResponse>(equipmentPositionHistory.Equipment);

        return new EquipmentPositionHistoryResponse(
            equipmentPositionHistory.Id,
            equipmentResponse,
            equipmentPositionHistory.Date,
            equipmentPositionHistory.Lat,
            equipmentPositionHistory.Lon);
    }

    public async Task<EquipmentPositionHistoryResponse> CreateEquipmentPositionHistoryAsync(CreateEquipmentPositionHistory createEquipmentPositionHistory)
    {
        ValidateDate(createEquipmentPositionHistory.Date);
        ValidateLatitude(createEquipmentPositionHistory.Lat);
        ValidateLongitude(createEquipmentPositionHistory.Lon);

        EquipmentResponse equipment = await _equipmentService.GetEquipmentByIdAsync(createEquipmentPositionHistory.EquipmentId);

        EquipmentPositionHistory equipmentPositionHistoryToCreate = _mapper.Map<EquipmentPositionHistory>(createEquipmentPositionHistory);
        _equipmentPositionHistoryRepository.Add(equipmentPositionHistoryToCreate);
        await _equipmentPositionHistoryRepository.CommitAsync();

        return new EquipmentPositionHistoryResponse(
            equipmentPositionHistoryToCreate.Id,
            equipment,
            equipmentPositionHistoryToCreate.Date,
            equipmentPositionHistoryToCreate.Lat,
            equipmentPositionHistoryToCreate.Lon);
    }

    public async Task<EquipmentPositionHistoryResponse> EditEquipmentPositionHistoryAsync(EditEquipmentPositionHistory editEquipmentPositionHistory, Guid routeId)
    {
        if (editEquipmentPositionHistory.Id != routeId)
            throw new BadRequestException("Id da rota não coincide com o id especificado.");

        EquipmentPositionHistory? equipmentPositionHistoryToEdit = await _equipmentPositionHistoryRepository.GetEquipmentPositionHistoryByIdAsync(editEquipmentPositionHistory.Id);
        if (equipmentPositionHistoryToEdit is null)
            throw new NotFoundException("Histórico de posição do equipamento com o id especificado não foi encontrado.");

        EquipmentResponse equipment = await _equipmentService.GetEquipmentByIdAsync(editEquipmentPositionHistory.EquipmentId);

        EquipmentPositionHistory editedEquipmentPositionHistory = _mapper.Map<EquipmentPositionHistory>(editEquipmentPositionHistory);
        _equipmentPositionHistoryRepository.Update(editedEquipmentPositionHistory);
        await _equipmentPositionHistoryRepository.CommitAsync();

        return new EquipmentPositionHistoryResponse(
            editedEquipmentPositionHistory.Id,
            equipment,
            editedEquipmentPositionHistory.Date,
            editedEquipmentPositionHistory.Lat,
            editedEquipmentPositionHistory.Lon);
    }

    public async Task DeleteEquipmentPositionHistoryAsync(Guid id)
    {
        EquipmentPositionHistory? equipmentPositionHistoryToDelete = await _equipmentPositionHistoryRepository.GetByIdAsync(id);
        if (equipmentPositionHistoryToDelete is null)
            throw new NotFoundException("Histórico de posição do equipamento com o id especificado não foi encontrado.");

        _equipmentPositionHistoryRepository.Delete(equipmentPositionHistoryToDelete);
        await _equipmentPositionHistoryRepository.CommitAsync();
    }

    private static void ValidateDate(DateOnly specifiedDate)
    {
        DateOnly todaysDate = DateOnly.FromDateTime(DateTime.Now);
        if (specifiedDate > todaysDate)
            throw new BadRequestException("Data não pode ser no futuro.");
    }

    private static void ValidateLatitude(decimal specifiedLatitude)
    {
        if (specifiedLatitude > 90 || specifiedLatitude < -90)
            throw new BadRequestException("Valor da latitude deve ser entre -90º e 90º");
    }

    private static void ValidateLongitude(decimal specifiedLongitude)
    {
        if (specifiedLongitude < 0 || specifiedLongitude > 180)
            throw new BadRequestException("Valor da longitude deve ser entre 0º e 180º");
    }
}
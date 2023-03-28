using AutoMapper;
using TesteTecnico.Application.Application.Common.Exceptions;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipamento;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Application.Services.Entities;

public class EquipmentService : IEquipmentService
{
    private readonly IEquipmentRepository _equipmentRepository;
    private readonly IEquipmentModelRepository _equipmentModelRepository;
    private readonly IEquipmentPositionHistoryRepository _equipmentPositionHistoryRepository;
    private readonly IMapper _mapper;
    public EquipmentService(
        IEquipmentRepository equipmentRepository,
        IMapper mapper,
        IEquipmentModelRepository equipmentModelRepository,
        IEquipmentPositionHistoryRepository equipmentPositionHistoryRepository)
    {
        _equipmentRepository = equipmentRepository;
        _mapper = mapper;
        _equipmentModelRepository = equipmentModelRepository;
        _equipmentPositionHistoryRepository = equipmentPositionHistoryRepository;
    }

    public async Task<EquipmentResponse> GetEquipmentByIdAsync(Guid equipmentId)
    {
        Equipment? equipment = await _equipmentRepository.GetEquipmentByIdAsync(equipmentId);
        if (equipment is null)
            throw new NotFoundException("Não foi possível encontrar um equipamento com o id especificado.");

        return _mapper.Map<EquipmentResponse>(equipment);
    }

    public async Task<EquipmentResponse> CreateEquipmentAsync(CreateEquipmentRequest equipmentRequest)
    {
        Equipment? equipment = await _equipmentRepository.GetEquipmentByNameAsync(equipmentRequest.Name.ToLower());
        if (equipment is not null)
            throw new ConflictException("Equipamento com o nome especificado já existe.");

        EquipmentModel? equipmentModel = await _equipmentModelRepository.GetEquipmentModelByIdAsync(equipmentRequest.EquipmentModelId);
        if (equipmentModel is null)
            throw new BadRequestException("Modelo de equipamento com o id especificado não existe.");

        Equipment equipmentToCreate = _mapper.Map<Equipment>(equipmentRequest);
        _equipmentRepository.Add(equipmentToCreate);
        await _equipmentRepository.CommitAsync();

        return new EquipmentResponse(
            Id: equipmentToCreate.Id,
            Name: equipmentToCreate.Name,
            EquipmentModel: equipmentModel);
    }

    public async Task<EquipmentResponse> EditEquipmentAsync(EditEquipmentRequest editEquipmentRequest, Guid routeId)
    {
        if (editEquipmentRequest.Id != routeId)
            throw new BadRequestException("Id da rota não coincide com o id especificado.");

        Equipment? equipment = await _equipmentRepository.GetEquipmentByIdAsync(editEquipmentRequest.Id, false);
        if (equipment is null)
            throw new NotFoundException("Equipamento com o id especificado não existe.");

        EquipmentModel? equipmentModel = await _equipmentModelRepository.GetEquipmentModelByIdAsync(editEquipmentRequest.EquipmentModelId);
        if (equipmentModel is null)
            throw new NotFoundException("Modelo com o id especificado não existe.");

        Equipment editedEquipment = _mapper.Map<Equipment>(editEquipmentRequest);
        _equipmentRepository.Update(editedEquipment);
        await _equipmentRepository.CommitAsync();

        return new EquipmentResponse(
            Id: editedEquipment.Id,
            Name: editedEquipment.Name,
            EquipmentModel: equipmentModel);
    }

    public async Task DeleteEquipmentAsync(Guid equipmentId)
    {
        Equipment? equipmentToDelete = await _equipmentRepository.GetEquipmentByIdAsync(equipmentId);
        if (equipmentToDelete is null)
            throw new NotFoundException("Equipamento com o id especificado não foi encontrado.");

        _equipmentRepository.Delete(equipmentToDelete);
        await _equipmentRepository.CommitAsync();
    }

    public async Task<EquipmentResponse> GetMostRecentEquipmentPosition(Guid equipmentId)
    {
        var mostRecentPosition = await _equipmentPositionHistoryRepository.GetMostRecentEquipmentPosition(equipmentId);
        if (mostRecentPosition is null)
            throw new NotFoundException("Equipamento ainda não possui uma posição cadastrada.");

        return new EquipmentResponse(
            Id: mostRecentPosition.EquipmentId,
            mostRecentPosition.Equipment.Name,
            mostRecentPosition.Equipment.EquipmentModel
        );
    }
}
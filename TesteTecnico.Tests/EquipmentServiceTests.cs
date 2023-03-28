using System;
using System.Threading.Tasks;
using FakeItEasy;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipamento;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;
using TesteTecnico.Application.Application.Services.Entities;
using AutoMapper;
using Xunit;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;
using TesteTecnico.Application.Domain.Entities;
using TesteTecnico.Application.Application.Common.Exceptions;

namespace TesteTecnico.Tests;

public class EquipmentServiceTests
{
    private readonly IEquipmentRepository _equipmentRepositoryMock;
    private readonly IMapper _mapperMock;
    private readonly IEquipmentModelRepository _equipmentModelRepositoryMock;
    private readonly IEquipmentPositionHistoryRepository _equipmentPositionHistoryRepositoryMock;
    private readonly IEquipmentService _sut;

    public EquipmentServiceTests()
    {
        _equipmentRepositoryMock = A.Fake<IEquipmentRepository>();
        _mapperMock = A.Fake<IMapper>();
        _equipmentModelRepositoryMock = A.Fake<IEquipmentModelRepository>();
        _equipmentPositionHistoryRepositoryMock = A.Fake<IEquipmentPositionHistoryRepository>();
        _sut = new EquipmentService(
            _equipmentRepositoryMock,
            _mapperMock,
            _equipmentModelRepositoryMock,
            _equipmentPositionHistoryRepositoryMock);
    }

    [Fact]
    public async Task Obter_Equipamento_Por_Id_Inexistente_Retorna_NotFoundException()
    {
        Guid equipmentId = Guid.NewGuid();
        Equipment? nullEquipment = null;
        A.CallTo(() => _equipmentRepositoryMock.GetEquipmentByIdAsync(equipmentId, true)).Returns(nullEquipment);

        async Task result() => await _sut.GetEquipmentByIdAsync(equipmentId);

        var exception = await Assert.ThrowsAsync<NotFoundException>(result);
        Assert.Equal("Não foi possível encontrar um equipamento com o id especificado.", exception.Message);
    }

    [Fact]
    public async Task Obter_Equipamento_Por_Id_Existente_Retorna_Equipamento()
    {
        Guid equipmentId = Guid.NewGuid();
        Guid equipmentModelId = Guid.NewGuid();
        EquipmentModel equipmentModel = GenerateEquipmentModel(equipmentModelId, "equipmentModel");
        Equipment expectedEquipment = GenerateEquipment(equipmentId, "equipment", equipmentModel);
        A.CallTo(() => _equipmentRepositoryMock.GetEquipmentByIdAsync(equipmentId, true)).Returns(expectedEquipment);
        EquipmentResponse expectedEquipmentResponse = GenerateEquipmentResponseFromEquipment(expectedEquipment);
        A.CallTo(() => _mapperMock.Map<EquipmentResponse>(expectedEquipment)).Returns(expectedEquipmentResponse);

        var equipment = await _sut.GetEquipmentByIdAsync(equipmentId);

        Assert.Equivalent(expectedEquipmentResponse, equipment);
    }

    [Fact]
    public async Task Registrar_Novo_Equipamento_Com_Nome_Ja_Existente_Retorna_ConflictException()
    {
        Guid equipmentModelId = Guid.NewGuid();
        CreateEquipmentRequest equipmentModelToCreate = GenerateCreateEquipmentRequest("name", equipmentModelId);
        EquipmentModel equipmentModel = GenerateEquipmentModel(Guid.NewGuid(), "equipmentModel");
        Equipment existentEquipment = GenerateEquipment(Guid.NewGuid(), equipmentModelToCreate.Name, equipmentModel);
        A.CallTo(() => _equipmentRepositoryMock.GetEquipmentByNameAsync(equipmentModelToCreate.Name)).Returns(existentEquipment);

        async Task result() => await _sut.CreateEquipmentAsync(equipmentModelToCreate);

        var exception = await Assert.ThrowsAsync<ConflictException>(result);
        Assert.Equal("Equipamento com o nome especificado já existe.", exception.Message);
    }

    [Fact]
    public async Task Registrar_Equipamento_Com_Modelo_Inexistente_Retorna_NotFoundException()
    {
        Guid equipmentModelId = Guid.NewGuid();
        CreateEquipmentRequest equipmentModelToCreate = GenerateCreateEquipmentRequest("name", equipmentModelId);
        Equipment? nullEquipment = null;
        A.CallTo(() => _equipmentRepositoryMock.GetEquipmentByNameAsync(equipmentModelToCreate.Name)).Returns(nullEquipment);
        EquipmentModel? nullEquipmentModel = null;
        A.CallTo(() => _equipmentModelRepositoryMock.GetEquipmentModelByIdAsync(equipmentModelId)).Returns(nullEquipmentModel);

        async Task result() => await _sut.CreateEquipmentAsync(equipmentModelToCreate);

        var exception = await Assert.ThrowsAsync<NotFoundException>(result);
        Assert.Equal("Modelo de equipamento com o id especificado não existe.", exception.Message);
    }

    [Fact]
    public async Task Registrar_Equipamento_Com_Dados_Validos_Retorna_Equipamento_Criado()
    {
        // Arrange
        Guid equipmentId = Guid.NewGuid();
        Guid equipmentModelId = Guid.NewGuid();

        CreateEquipmentRequest equipmentModelToCreate = GenerateCreateEquipmentRequest("name", equipmentModelId);
        Equipment? nullEquipment = null;

        A.CallTo(() => _equipmentRepositoryMock.GetEquipmentByNameAsync(equipmentModelToCreate.Name)).Returns(nullEquipment);
        EquipmentModel equipmentModel = GenerateEquipmentModel(equipmentModelId, "name");
        A.CallTo(() => _equipmentModelRepositoryMock.GetEquipmentModelByIdAsync(equipmentModelId)).Returns(equipmentModel);
        Equipment equipmentToCreate = GenerateEquipment(equipmentId, "name", equipmentModel);
        A.CallTo(() => _mapperMock.Map<Equipment>(equipmentModelToCreate)).Returns(equipmentToCreate);
        EquipmentResponse expectedEquipmentResponse = GenerateEquipmentResponseFromEquipment(equipmentToCreate);

        // Act
        var equipmentResponse = await _sut.CreateEquipmentAsync(equipmentModelToCreate);

        // Assert
        Assert.Equivalent(expectedEquipmentResponse, equipmentResponse);
    }

    [Fact]
    public async Task Editar_Equipamento_Com_Id_Da_Rota_Diferente_Retorna_BadRequestException()
    {
        Guid routeId = Guid.NewGuid();
        Guid equipmentId = Guid.NewGuid();
        Guid equipmentModelId = Guid.NewGuid();
        EditEquipmentRequest editEquipmentRequest = GenerateEditEquipmentRequest(equipmentId, "name", equipmentModelId);

        async Task result() => await _sut.EditEquipmentAsync(editEquipmentRequest, routeId);

        var exception = await Assert.ThrowsAsync<BadRequestException>(result);
        Assert.Equal("Id da rota não coincide com o id especificado.", exception.Message);
    }

    [Fact]
    public async Task Editar_Equipamento_Inexistente_Retorna_NotFoundException()
    {
        Guid equipmentId = Guid.NewGuid();
        Guid equipmentModelId = Guid.NewGuid();
        EditEquipmentRequest editEquipmentRequest = GenerateEditEquipmentRequest(equipmentId, "name", equipmentModelId);
        Equipment? nullEquipment = null;
        A.CallTo(() => _equipmentRepositoryMock.GetEquipmentByIdAsync(equipmentId, false)).Returns(nullEquipment);

        async Task result() => await _sut.EditEquipmentAsync(editEquipmentRequest, equipmentId);

        var exception = await Assert.ThrowsAsync<NotFoundException>(result);
        Assert.Equal("Equipamento com o id especificado não existe.", exception.Message);
    }

    [Fact]
    public async Task Editar_Equipamento_Com_Modelo_Invalido_Retorna_NotFoundException()
    {
        Guid equipmentId = Guid.NewGuid();
        Guid equipmentModelId = Guid.NewGuid();
        EditEquipmentRequest editEquipmentRequest = GenerateEditEquipmentRequest(equipmentId, "name", equipmentModelId);
        EquipmentModel equipmentModel = GenerateEquipmentModel(equipmentModelId, "name");
        Equipment equipment = GenerateEquipment(equipmentId, "name", equipmentModel);
        A.CallTo(() => _equipmentRepositoryMock.GetEquipmentByIdAsync(equipmentId, false)).Returns(equipment);
        EquipmentModel? nullEquipmentModel = null;
        A.CallTo(() => _equipmentModelRepositoryMock.GetEquipmentModelByIdAsync(equipmentModelId)).Returns(nullEquipmentModel);

        async Task result() => await _sut.EditEquipmentAsync(editEquipmentRequest, equipmentId);

        var exception = await Assert.ThrowsAsync<NotFoundException>(result);
        Assert.Equal("Modelo com o id especificado não existe.", exception.Message);
    }

    [Fact]
    public async Task Editar_Equipamento_Retorna_Equipamento_Editado()
    {
        Guid equipmentId = Guid.NewGuid();
        Guid equipmentModelId = Guid.NewGuid();
        EditEquipmentRequest editEquipmentRequest = GenerateEditEquipmentRequest(equipmentId, "name", equipmentModelId);
        EquipmentModel equipmentModel = GenerateEquipmentModel(equipmentModelId, "name");
        Equipment equipment = GenerateEquipment(equipmentId, "name", equipmentModel);
        A.CallTo(() => _equipmentRepositoryMock.GetEquipmentByIdAsync(equipmentId, false)).Returns(equipment);
        A.CallTo(() => _mapperMock.Map<Equipment>(editEquipmentRequest)).Returns(equipment);
        EquipmentResponse expectedEquipmentResponse = GenerateEquipmentResponseFromEquipment(equipment);

        var equipmentResponse = await _sut.EditEquipmentAsync(editEquipmentRequest, equipmentId);

        Assert.Equivalent(expectedEquipmentResponse, equipmentResponse);
    }

    private static EquipmentModel GenerateEquipmentModel(Guid id, string name)
    {
        return new EquipmentModel()
        {
            Id = id,
            Name = name
        };
    }

    private static Equipment GenerateEquipment(Guid equipmentId, string name, EquipmentModel equipmentModel)
    {
        return new Equipment()
        {
            Id = equipmentId,
            Name = name,
            EquipmentModel = equipmentModel,
            EquipmentModelId = equipmentModel.Id
        };
    }

    private static EquipmentResponse GenerateEquipmentResponseFromEquipment(Equipment equipment)
    {
        return new EquipmentResponse(Id: equipment.Id, Name: equipment.Name, EquipmentModel: equipment.EquipmentModel);
    }

    private static CreateEquipmentRequest GenerateCreateEquipmentRequest(string name, Guid equipmentModelId)
    {
        return new CreateEquipmentRequest(name, equipmentModelId);
    }

    private static EditEquipmentRequest GenerateEditEquipmentRequest(Guid id, string name, Guid equipmentModelId)
    {
        return new EditEquipmentRequest(id, name, equipmentModelId);
    }
}
using AutoMapper;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStates;
using TesteTecnico.Application.Domain.Entities;

namespace TesteTecnico.Application.Infrastructure.Persistence.DataContext.Mappings;

public class MappingProfile : Profile
{
    public MappingProfile()
    {
        CreateMap<Equipment, EquipmentResponse>().ReverseMap();
        CreateMap<CreateEquipmentRequest, Equipment>().ReverseMap();
        CreateMap<EditEquipmentRequest, Equipment>().ReverseMap();

        CreateMap<CreateEquipmentModel, EquipmentModel>().ReverseMap();
        CreateMap<EditEquipmentModel, EquipmentModel>().ReverseMap();
        CreateMap<EquipmentModel, EquipmentModelResponse>().ReverseMap();

        CreateMap<CreateEquipmentState, EquipmentState>().ReverseMap();
        CreateMap<EditEquipmentState, EquipmentState>().ReverseMap();
        CreateMap<EquipmentState, EquipmentStateResponse>().ReverseMap();
    }
}
global using Microsoft.EntityFrameworkCore;
using AutoMapper;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipamento;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentModels;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentPositionHistories;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.Equipments;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStateHistories;
using TesteTecnico.Application.Application.Common.Interfaces.Entities.EquipmentStates;
using TesteTecnico.Application.Application.Middlewares;
using TesteTecnico.Application.Application.Services.Entities;
using TesteTecnico.Application.Infrastructure.Persistence;
using TesteTecnico.Application.Infrastructure.Persistence.DataContext;
using TesteTecnico.Application.Infrastructure.Persistence.DataContext.Mappings;

var mappingConfig = new MapperConfiguration(mc => mc.AddProfile(new MappingProfile()));
IMapper mapper = mappingConfig.CreateMapper();

var builder = WebApplication.CreateBuilder(args);
{
    builder.Services.AddDbContext<AppDbContext>(options => options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

    builder.Services.AddSingleton(mapper);

    builder.Services.AddScoped<IEquipmentService, EquipmentService>();
    builder.Services.AddScoped<IEquipmentRepository, EquipmentRepository>();
    builder.Services.AddScoped<IEquipmentModelService, EquipmentModelService>();
    builder.Services.AddScoped<IEquipmentModelRepository, EquipmentModelRepository>();
    builder.Services.AddScoped<IEquipmentStateService, EquipmentStateService>();
    builder.Services.AddScoped<IEquipmentStateRepository, EquipmentStateRepository>();
    builder.Services.AddScoped<IEquipmentPositionHistoryService, EquipmentPositionHistoryService>();
    builder.Services.AddScoped<IEquipmentPositionHistoryRepository, EquipmentPositionHistoryRepository>();
    builder.Services.AddScoped<IEquipmentStateHistoryService, EquipmentStateHistoryService>();
    builder.Services.AddScoped<IEquipmentStateHistoryRepository, EquipmentStateHistoryRepository>();
}

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

app.UseMiddleware<ErrorHandlerMiddleware>();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();

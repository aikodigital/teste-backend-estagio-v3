using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Interfaces.Services;
using EquipmentDomain.Models;
using EquipmentDomain.Services;
using EquipmentInfra.Repositories;
using Microsoft.EntityFrameworkCore;
using System;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddDbContext<DbequipamentoContext>(options => options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

builder.Services.AddScoped<IEquipmentService, EquipmentService>();
builder.Services.AddScoped<IEquipmentRepository, EquipmentRepository>();

builder.Services.AddScoped<IEquipmentStateService, EquipmentStateService>();
builder.Services.AddScoped<IEquipmentStateRepository, EquipmentStateRepository>();

builder.Services.AddScoped<IEquipmenteModelService, EquipmenteModelService>();
builder.Services.AddScoped<IEquipmentModelRepository, EquipmentModelRepository>();

builder.Services.AddScoped<IEquipmentModelStateHourlyEarningService, EquipmentModelStateHourlyEarningService>();
builder.Services.AddScoped<IEquipmentModelStateHourlyEarningRepository, EquipmentModelStateHourlyEarningRepository>();

builder.Services.AddScoped<IEquipmentPositionHistoryService, EquipmentPositionHistoryService>();
builder.Services.AddScoped<IEquipmentPositionHistoryRepository, EquipmentPositionHistoryRepository>();

builder.Services.AddScoped<IEquipmentStateHistoryService, EquipmentStateHistoryService>();
builder.Services.AddScoped<IEquipmentStateHistoryRepository, EquipmentStateHistoryRepository>();

builder.Services.AddScoped<IEquipmentQueriesService, EquipmentQueriesService>();
builder.Services.AddScoped<IEquipmentQueriesRespository, EquipmentQueriesRepository>();

var app = builder.Build();


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

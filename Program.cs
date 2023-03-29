using API_CRUD_Test.Data;
using API_CRUD_Test.Repository;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddDbContext<EquipmentContext>(options =>
        options.UseNpgsql(connectionString));
builder.Services.AddScoped<IEquipment, EquipmentRepository>();
builder.Services.AddScoped<IEquipmentModel, EquipmentModelRepository>();
builder.Services.AddScoped<IEquipmentState, EquipmentStateRepository>();
builder.Services.AddScoped<IEquipmentModelStateHourlyEarnings, EquipmentModelStateHourlyEarningsRepository>();
builder.Services.AddScoped<IEquipmentStateHistory, EquipmentStateHistoryRepository>();
builder.Services.AddScoped<IEquipmentPositionHistory, EquipmentPositionHistoryRepository>();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();

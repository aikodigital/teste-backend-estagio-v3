using Microsoft.EntityFrameworkCore;
using src_teste_backend_estagio_v3.Context;
using src_teste_backend_estagio_v3.Models;
using Npgsql;
using Swashbuckle.AspNetCore.Swagger;
using System.Reflection.Metadata;
using Microsoft.EntityFrameworkCore.Update;
using Microsoft.AspNetCore.Mvc;

namespace src_teste_backend_estagio_v3
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);
            builder.Services.AddEntityFrameworkNpgsql().AddDbContext<ForestOpContext>(options => options.UseNpgsql());
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();
            var app = builder.Build();

            /*
             * Equipment mapping
             */

            //Create equipment
            app.MapPost("/equipment/", async ([FromBody] Equipment equipment, ForestOpContext context) =>
            {
                context.Equipment.Add(equipment);
                await context.SaveChangesAsync();

                return Results.Created($"/equipment/{equipment.Id}", equipment);
            });

            //Read equipment by id
            app.MapGet("/equipment/{id:Guid}", async ([FromRoute] Guid id, ForestOpContext context) =>
            {
                return await context.Equipment.FindAsync(id);
            });

            //Update equipment
            app.MapPut("/equipment/{id:Guid}", async ([FromBody] Equipment equipment, ForestOpContext context) =>
            {
                var contextEquipment = await context.Equipment.FindAsync(equipment.Id);

                if (contextEquipment is null) return Results.NotFound();

                contextEquipment.Name = equipment.Name;
                contextEquipment.Id = equipment.Id;

                await context.SaveChangesAsync();
                return Results.NoContent();
            });

            //Delete equipment
            app.MapDelete("/equipment/{id:Guid}", async ([FromBody] Equipment equipment, ForestOpContext context) =>
            {
                var contextEquipment = context.Equipment.Find(equipment.Id);
                if (contextEquipment is not null) 
                {
                    context.Equipment.Remove(contextEquipment);
                    await context.SaveChangesAsync();
                    return Results.Ok(equipment);
                }
                return Results.NoContent();
            });


            /*
             * Equipment_model mapping
             */

            //Create equipment_model
            app.MapPost("/equipment_model", async ([FromServices] EquipmentModel equipmentModel, ForestOpContext context) =>
            {
                context.EquipmentModels.Add(equipmentModel);
                await context.SaveChangesAsync();

                return Results.Created($"/equipment_model/{equipmentModel.Id}", equipmentModel);
            });

            //Read equipment_model by id
            app.MapGet("/equipment_model/{id:Guid}", async ([FromRoute] Guid id, ForestOpContext context) =>
            {
                return await context.EquipmentModels.FindAsync(id);
            });

            //Update equipment_model
            app.MapPut("/equipment_model/{id:Guid}", async ([FromBody] EquipmentModel equipmentModel, ForestOpContext context) =>
            {
                var contextEquipmentModel = await context.EquipmentModels.FindAsync(equipmentModel.Id);

                if (contextEquipmentModel is null) return Results.NotFound();

                contextEquipmentModel.Name = equipmentModel.Name;
                contextEquipmentModel.Id = equipmentModel.Id;

                await context.SaveChangesAsync();
                return Results.NoContent();
            });

            //Delete equipment_model
            app.MapDelete("/equipment_model/{id:Guid}", async ([FromBody] EquipmentModel equipmentModel, ForestOpContext context) =>
            {
                var contextEquipmentModel = context.EquipmentModels.Find(equipmentModel.Id);
                if (contextEquipmentModel is not null)
                {
                    context.EquipmentModels.Remove(contextEquipmentModel);
                    await context.SaveChangesAsync();
                    Results.Ok(equipmentModel);
                }
                return Results.NotFound();
            });


            /*
             * Equipment_model_state_hourly_earning mapping
             */

            //Create Equipment_model_state_hourly_earning
            app.MapPost("/equipment_state_earning", async ([FromBody] EquipmentModelStateHourlyEarning equipmentStateEarning, ForestOpContext context) =>
            {
                context.EquipmentModelStateHourlyEarnings.Add(equipmentStateEarning);
                await context.SaveChangesAsync();

                return Results.Created($"/equipment_state_earning/{equipmentStateEarning.EquipmentModelId}{equipmentStateEarning.EquipmentStateId}", equipmentStateEarning);
            });

            //Read Equipment_model_state_hourly_earning by model id and state id
            app.MapGet("/equipment_state_earning/{modelId:Guid}&{stateId:Guid}", async ([FromRoute]Guid modelId, [FromRoute] Guid stateId, ForestOpContext context) =>
            {
                return await context.EquipmentModelStateHourlyEarnings.Where(e => e.EquipmentModelId == modelId && e.EquipmentStateId == stateId).FirstAsync();
            });

            //Update Equipment_model_state_hourly_earning
            app.MapPut("/equipment_state_earning/{modelId:Guid}&{stateId:Guid}", async ([FromRoute] Guid modelId, [FromRoute] Guid stateId, [FromBody] EquipmentModelStateHourlyEarning equipmentStateEarning, ForestOpContext context) =>
            {
                var contextEquipmentStateEarning = context.EquipmentModelStateHourlyEarnings.Where(e => e.EquipmentModelId == modelId && e.EquipmentStateId == stateId).First();

                if (contextEquipmentStateEarning is null) return Results.NotFound();

                contextEquipmentStateEarning.EquipmentModelId = equipmentStateEarning.EquipmentModelId;
                contextEquipmentStateEarning.EquipmentStateId = equipmentStateEarning.EquipmentStateId;

                await context.SaveChangesAsync();
                return Results.NoContent();
            });

            //Delete Equipment_model_state_hourly_earning
            app.MapDelete("/equipment_state_earning/{modelId:Guid}&{stateId:Guid}", async ([FromRoute] Guid modelId, [FromRoute] Guid stateId, [FromBody] EquipmentModelStateHourlyEarning equipmentStateEarning, ForestOpContext context) =>
            {
                var contextEquipmentStateEarning = context.EquipmentModelStateHourlyEarnings.Where(e => e.EquipmentModelId == modelId && e.EquipmentStateId == stateId).First();
                if (contextEquipmentStateEarning is not null)
                {
                    context.EquipmentModelStateHourlyEarnings.Remove(contextEquipmentStateEarning);
                    await context.SaveChangesAsync();
                    Results.Ok(equipmentStateEarning);
                }
                return Results.NoContent();
            });

            /*
             * Equipment_position_history mapping
             */

            //Create equipment_position_history
            app.MapPost("/equipment_position_history", async ([FromBody] EquipmentPositionHistory equipmentPosition, ForestOpContext context) =>
            {
                context.EquipmentPositionHistories.Add(equipmentPosition);
                await context.SaveChangesAsync();

                return Results.Created($"/equipment_position_history/{equipmentPosition.EquipmentId}", equipmentPosition);
            });

            //Read equipment_position_history by id
            app.MapGet("/equipment_position_history/{id:Guid}&{date:DateTime}", async ([FromRoute] Guid id, [FromRoute] DateTime date, [FromBody] EquipmentPositionHistory equipmentPosition, ForestOpContext context) =>
            {
                return await context.EquipmentPositionHistories.Where(h => h.EquipmentId == id && h.Date == date).FirstAsync();
            });

            //Update equipment_position_history
            app.MapPut("/equipment_position_history/{id:Guid}&{date:DateTime}", async ([FromRoute] Guid id, [FromRoute] DateTime date, [FromBody] EquipmentPositionHistory equipmentPosition, ForestOpContext context) =>
            {
                var contextEquipmentPosition = await context.EquipmentPositionHistories.Where(h => h.EquipmentId == id && h.Date == date).FirstAsync();

                if (contextEquipmentPosition is null) return Results.NotFound();

                contextEquipmentPosition.EquipmentId = equipmentPosition.EquipmentId;
                contextEquipmentPosition.Date = equipmentPosition.Date;
                contextEquipmentPosition.Lat = equipmentPosition.Lat;
                contextEquipmentPosition.Lon = equipmentPosition.Lon;

                await context.SaveChangesAsync();
                return Results.NoContent();
            });

            //Delete equipment_model
            app.MapDelete("/equipment_position_history/{id:Guid}", async ([FromRoute] Guid id, [FromBody] EquipmentPositionHistory equipmentPosition, ForestOpContext context) =>
            {
                var contextEquipmentPosition = context.EquipmentPositionHistories.Where(h => h.EquipmentId == id).First();
                if (contextEquipmentPosition is not null)
                {
                    context.EquipmentPositionHistories.Remove(contextEquipmentPosition);
                    await context.SaveChangesAsync();
                    Results.Ok(equipmentPosition);
                }
                return Results.NoContent();
            });

            /*
             * Equipment_state mapping
             */

            //Create equipment_state
            //Read equipment_state
            //Update equipment_state
            //Delete equipment_state




            /*
             * Equipment_state_history mapping
             */

            //Create Equipment_state_history
            //Read Equipment_state_history
            //Update Equipment_state_history
            //Delete Equipment_state_history


            app.UseSwagger();
            app.UseSwaggerUI();
            app.Run();
        }
    }
}
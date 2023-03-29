using Microsoft.EntityFrameworkCore;
using src_teste_backend_estagio_v3.Context;
using src_teste_backend_estagio_v3.Models;
using Npgsql;
using Swashbuckle.AspNetCore.Swagger;
using System.Reflection.Metadata;
using Microsoft.EntityFrameworkCore.Update;
using Microsoft.AspNetCore.Mvc;
using System;
using static System.Runtime.InteropServices.JavaScript.JSType;
using Microsoft.AspNetCore.Mvc.Rendering;

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
            app.MapPut("/equipment/{id:Guid}", async ([FromRoute] Guid id, [FromBody] Equipment equipment, ForestOpContext context) =>
            {
                var contextEquipment = await context.Equipment.FindAsync(id);

                if (contextEquipment is null) return Results.NotFound();

                contextEquipment.Name = equipment.Name;
                contextEquipment.Id = equipment.Id;

                await context.SaveChangesAsync();
                return Results.NoContent();
            });

            //Delete equipment
            app.MapDelete("/equipment/{id:Guid}", async ([FromRoute] Guid id, ForestOpContext context) =>
            {
                var contextEquipment = context.Equipment.Find(id);
                if (contextEquipment is not null) 
                {
                    context.Equipment.Remove(contextEquipment);
                    await context.SaveChangesAsync();
                    return Results.NoContent();
                }
                return Results.NotFound();
            });


            /*
             * Equipment_model mapping
             */

            //Create equipment_model
            app.MapPost("/equipment_model", async ([FromBody] EquipmentModel equipmentModel, ForestOpContext context) =>
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
            app.MapPut("/equipment_model/{id:Guid}", async ([FromRoute] Guid id, [FromBody] EquipmentModel equipmentModel, ForestOpContext context) =>
            {
                var contextEquipmentModel = await context.EquipmentModels.FindAsync(id);

                if (contextEquipmentModel is null) return Results.NotFound();

                contextEquipmentModel.Name = equipmentModel.Name;
                contextEquipmentModel.Id = equipmentModel.Id;

                await context.SaveChangesAsync();
                return Results.NoContent();
            });

            //Delete equipment_model
            app.MapDelete("/equipment_model/{id:Guid}", async ([FromRoute] Guid id, ForestOpContext context) =>
            {
                var contextEquipmentModel = context.EquipmentModels.Find(id);
                if (contextEquipmentModel is not null)
                {
                    context.EquipmentModels.Remove(contextEquipmentModel);
                    await context.SaveChangesAsync();
                    return Results.NoContent();
                }
                return Results.NotFound();
            });


            /*
             * Equipment_model_state_hourly_earning mapping
             */

            //Create Equipment_model_state_hourly_earning
            app.MapPost("/equipment_state_earning", async ([FromBody] EquipmentModelStateHourlyEarning equipmentStateEarning, ForestOpContext context) =>
            {
                string sql = @$" INSERT INTO operation.equipment_model_state_hourly_earnings(equipment_model_id, equipment_state_id, value)
                                 VALUES ('{equipmentStateEarning.EquipmentModelId}', '{equipmentStateEarning.EquipmentStateId}', {equipmentStateEarning.Value});";

                await context.Database.ExecuteSqlRawAsync(sql);
                return Results.Created($"/equipment_state_earning/{equipmentStateEarning.EquipmentModelId}&{equipmentStateEarning.EquipmentStateId}", equipmentStateEarning);
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
            app.MapDelete("/equipment_state_earning/{modelId:Guid}&{stateId:Guid}", async ([FromRoute] Guid modelId, [FromRoute] Guid stateId,ForestOpContext context) =>
            {
                string sql = @$"DELETE FROM operation.equipment_model_state_hourly_earnings
                                WHERE equipment_model_id = '{modelId}' AND equipment_state_id = '{stateId}';";
                await context.Database.ExecuteSqlRawAsync(sql);
                return Results.NoContent();
            });

            /*
             * Equipment_position_history mapping
             */

            //Create equipment_position_history
            app.MapPost("/equipment_position_history", async ([FromBody] EquipmentPositionHistory equipmentPosition, ForestOpContext context) =>
            {
                string sql = @$"INSERT INTO operation.equipment_position_history (equipment_id, date, lat, lon) 
	                            VALUES ('{equipmentPosition.EquipmentId}', '{equipmentPosition.Date}',{equipmentPosition.Lat},{equipmentPosition.Lon});";
                await context.Database.ExecuteSqlRawAsync(sql);
                return Results.Created($"/equipment_position_history/{equipmentPosition.EquipmentId}", equipmentPosition);
            });

            //Read equipment_position_history by id
            app.MapGet("/equipment_position_history/{id:Guid}&{date:DateTime}", async ([FromRoute] Guid id, [FromRoute] DateTime date, ForestOpContext context) =>
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
            app.MapDelete("/equipment_position_history/{id:Guid}&{date:DateTime}", async ([FromRoute] Guid id, [FromRoute] DateTime date, ForestOpContext context) =>
            {
                string sql = @$"DELETE FROM operation.equipment_position_history
                                WHERE equipment_id = '{id}' AND date = '{date}';";
                await context.Database.ExecuteSqlRawAsync(sql);
                return Results.NoContent();
            });

            /*
             * Equipment_state mapping
             */

            //Create equipment_state
            app.MapPost("/equipment_state/", async ([FromBody] EquipmentState equipmentState, ForestOpContext context) =>
            {
                context.EquipmentStates.Add(equipmentState);
                await context.SaveChangesAsync();

                return Results.Created($"/equipment_states/{equipmentState.Id}", equipmentState);
            });

            //Read equipment_state
            app.MapGet("/equipment_state/{id:Guid}", async ([FromRoute] Guid id, ForestOpContext context) =>
            {
                return await context.EquipmentStates.FindAsync(id);
            });

            //Update equipment_state
            app.MapPut("/equipment_state/{id:Guid}", async ([FromRoute] Guid id, [FromBody] EquipmentState equipmentState, ForestOpContext context) =>
            {
                var contextEquipmentState = await context.EquipmentStates.FindAsync(id);

                if (contextEquipmentState is null) return Results.NotFound();

                contextEquipmentState.Name = equipmentState.Name;
                contextEquipmentState.Id = equipmentState.Id;
                contextEquipmentState.Color = equipmentState.Color;

                await context.SaveChangesAsync();
                return Results.NoContent();
            });
            //Delete equipment_state
            app.MapDelete("/equipment_state/{id:Guid}", async ([FromRoute] Guid id, ForestOpContext context) =>
            {
                var contextEquipmentState = context.EquipmentStates.Find(id);
                if (contextEquipmentState is not null)
                {
                    context.EquipmentStates.Remove(contextEquipmentState);
                    await context.SaveChangesAsync();
                    return Results.NoContent();
                }
                return Results.NotFound();
            });



            /*
             * Equipment_state_history mapping
             */

            //Create Equipment_state_history
            app.MapPost("/equipment_state_history", async ([FromBody] EquipmentStateHistory equipmentStateHistory, ForestOpContext context) =>
            {
                string sql = @$"INSERT INTO operation.equipment_position_history (equipment_id, date, equipment_state_id) 
	                            VALUES ('{equipmentStateHistory.EquipmentId}', '{equipmentStateHistory.Date}', '{equipmentStateHistory.EquipmentStateId}');";
                await context.Database.ExecuteSqlRawAsync(sql);
                return Results.Created($"/equipment_state_history/{equipmentStateHistory.EquipmentId}", equipmentStateHistory);
            });

            //Read Equipment_state_history
            app.MapGet("/equipment_state_history/{id:Guid}&{date:DateTime}", async ([FromRoute] Guid id, [FromRoute] DateTime date, ForestOpContext context) =>
            {
                return await context.EquipmentStateHistories.Where(h => h.EquipmentId == id && h.Date == date).FirstAsync();
            });

            //Update Equipment_state_history
            app.MapPut("/equipment_state_history/{id:Guid}&{date:DateTime}", async ([FromRoute] Guid id, [FromRoute] DateTime date, [FromBody] EquipmentStateHistory equipmentStateHistory, ForestOpContext context) =>
            {
                var contextEquipmentStateHistory = await context.EquipmentStateHistories.Where(h => h.EquipmentId == id && h.Date == date).FirstAsync();

                if (contextEquipmentStateHistory is null) return Results.NotFound();

                contextEquipmentStateHistory.EquipmentId = equipmentStateHistory.EquipmentId;
                contextEquipmentStateHistory.Date = equipmentStateHistory.Date;
                contextEquipmentStateHistory.EquipmentStateId = equipmentStateHistory.EquipmentStateId;

                await context.SaveChangesAsync();
                return Results.NoContent();
            });

            //Delete Equipment_state_history
            app.MapDelete("/equipment_state_history/{id:Guid}&{date:DateTime}", async ([FromRoute] Guid id, [FromRoute] DateTime date, ForestOpContext context) =>
            {
                string sql = @$"DELETE FROM operation.equipment_state_history
                                WHERE equipment_id = '{id}' AND date = '{date}';";
                await context.Database.ExecuteSqlRawAsync(sql);
                return Results.NoContent();
            });


            /*
             * Endpoint of equipment actual state
             */

            //Read equipment_actual_state
            app.MapGet("/equipment_actual_state", (ForestOpContext context) => {
                List<EquipmentStateHistory> list = context.EquipmentStateHistories.FromSql($@"SELECT distinct on (equipment_id) equipment_id, date, equipment_state_id 
                                                                                              FROM operation.equipment_state_history 
                                                                                              ORDER BY equipment_id, date DESC;").ToList();
                return list;
            });

            /*
             * Endpoint of actual position by equipment
             */

            //Read actual_position_by_equipment
            app.MapGet("/actual_position_by_equipment", async (ForestOpContext context) => {
                List<EquipmentPositionHistory> list = context.EquipmentPositionHistories.FromSql($@"SELECT distinct on (equipment_id) equipment_id, date, lat, lon 
                                                                                                    FROM operation.equipment_position_history 
                                                                                                    ORDER BY equipment_id, date desc;").ToList();
                return list;
            });


            app.UseSwagger();
            app.UseSwaggerUI();
            app.Run();
        }
    }
}
using Microsoft.EntityFrameworkCore;
using src_teste_backend_estagio_v3.Context;
using src_teste_backend_estagio_v3.Models;
using Npgsql;
using Swashbuckle.AspNetCore.Swagger;

namespace src_teste_backend_estagio_v3
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);
            Console.WriteLine("Write the password to access the database");
            var password = Console.ReadLine();
            var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");
            connectionString.Insert(83, password);
            builder.Services.AddEntityFrameworkNpgsql().AddDbContext<ForestOpContext>(options => options.UseNpgsql(connectionString));
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();
            var app = builder.Build();

            //READ equipment by id
            app.MapGet("/equipment/{id:Guid}", async (Guid id, ForestOpContext context) =>
            {
                return await context.Equipment.FindAsync(id);
            });

            //CREATE equipment
            app.MapPost("/equipment/", async (Equipment equipment, ForestOpContext context) =>
            {
                context.Equipment.Add(equipment);
                await context.SaveChangesAsync();

                return Results.Created($"/equipment/{equipment.Id}", equipment);
            });

            //DELETE equipment
            app.MapDelete("/equipment/{id:Guid}", async (Guid id, ForestOpContext context) =>
            {
                var equipment = context.Equipment.Find(id);
                if (equipment != null) 
                {
                    context.Equipment.Remove(equipment);
                    await context.SaveChangesAsync();
                }
                return Results.NoContent();
            });

            //UPDATE equipment
            app.MapPut("/equipment/{id:Guid}", async (Guid id, Equipment equipment, ForestOpContext context) =>
            {
                if(equipment.Id != id)
                {
                    return Results.BadRequest();
                }

                var contextEquipment = await context.Equipment.FindAsync(id);

                if (contextEquipment is null) return Results.NotFound();

                contextEquipment.Name = equipment.Name;
                contextEquipment.Id = equipment.Id;
                contextEquipment.EquipmentModelId = equipment.EquipmentModelId;

                await context.SaveChangesAsync();
                return Results.Ok(contextEquipment);

            });

            app.UseSwagger();
            app.UseSwaggerUI();
            app.Run();
        }
    }
}
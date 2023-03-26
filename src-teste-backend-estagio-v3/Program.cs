using Microsoft.EntityFrameworkCore;
using src_teste_backend_estagio_v3.Context;
using src_teste_backend_estagio_v3.Models;
using System.Linq;
using Npgsql;

namespace src_teste_backend_estagio_v3
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);
            Console.WriteLine("Write the password to the user of the database");
            //Just hitting Enter will do
            var password = Console.ReadLine();
            var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");
            connectionString.Insert(83, password);
            builder.Services.AddEntityFrameworkNpgsql().AddDbContext<ForestOpContext>(options => options.UseNpgsql(connectionString));
            var app = builder.Build();

            app.MapGet("/", () => "Hello World!");


            //return all equipments
            app.MapGet("/equipment/", async (ForestOpContext db) =>
            {
                await db.Equipment.ToListAsync();
            });



            app.Run();
        }
    }
}
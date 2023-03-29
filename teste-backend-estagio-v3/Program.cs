using Microsoft.EntityFrameworkCore;
using teste_backend_estagio_v3.Data;
using System;
using Microsoft.Extensions.Configuration;

namespace teste_backend_estagio_v3
{
	public class Program
	{
		public static void Main(string[] args)
		{
			var builder = WebApplication.CreateBuilder(args);
			builder.Services.AddControllersWithViews();
			
			// Add services to the container.

			builder.Services.AddControllers();
			// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
			builder.Services.AddEndpointsApiExplorer();
			builder.Services.AddSwaggerGen();

			builder.Services.AddEntityFrameworkNpgsql()
			.AddDbContext<teste_backend_estagioDBContext>
			(options => options.UseNpgsql("Host=localhost;Port=5432;User Id=postgres;Password=40021!;Database=vaga-aiko;"));


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
		}
	}
}
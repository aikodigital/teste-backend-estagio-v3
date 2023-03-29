using API_CRUD_Test.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using System.ComponentModel.DataAnnotations.Schema;

namespace API_CRUD_Test.Data
{
    public class EquipmentContext : DbContext
    {
        public EquipmentContext(DbContextOptions<EquipmentContext> options) : base(options) { }

        //Aqui temos o Schema padrão que no banco de dados é definido como operation
        public static string DefaultSchema = "operation";
        // -- A partir desse ponto o DbSet de nome X faz acesso ao x da tabela usando como referência a classe X.
        public DbSet<Equipment> Equipment { get; set; } //Ex: DbSet Equipment acessa equipment com a classe Equipment.cs como referencia
        public DbSet<EquipmentModel> EquipmentModel { get; set; }
        public DbSet<EquipmentModelStateHourlyEarnings> EquipmentModelStateHourlyEarnings { get; set; }
        public DbSet<EquipmentPositionHistory> EquipmentPositionHistory { get; set; }
        public DbSet<EquipmentState> EquipmentState { get; set;}
        public DbSet<EquipmentStateHistory> EquipmentStateHistory { get;set; }
        //----------------------- Objetos Sem Chave Primária -------------------------------------------
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.HasDefaultSchema("operation");
            modelBuilder.Entity<Equipment>().ToTable("equipment");

            modelBuilder.Entity<EquipmentModelStateHourlyEarnings>().HasKey(e => new
            {
                e.EquipmentStateId,
                e.EquipmentModelId
            });
            modelBuilder.Entity<EquipmentPositionHistory>().HasNoKey();
            modelBuilder.Entity<EquipmentStateHistory>().HasKey(e => new
            {
                e.EquipmentId,
                e.EquipmentStateId
            });
        }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    => optionsBuilder
        .UseSnakeCaseNamingConvention();//Voltar aqui

    }
}

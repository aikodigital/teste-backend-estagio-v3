using Microsoft.EntityFrameworkCore;


namespace PostgreAPI.Models
{
    public partial class AikoAPIContext : DbContext
    {
        public static string ConnectionString;
        public AikoAPIContext()
        {
        }

        public AikoAPIContext(DbContextOptions<AikoAPIContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Equipment> Equipment { get; set; }

        public virtual DbSet<EquipmentModel> EquipmentModels { get; set; }

        public virtual DbSet<EquipmentModelStateHourEarn> EquipmentModelStateHourEarn { get; set; }

        public virtual DbSet<EquipmentPositHistory> EquipmentPositHistory { get; set; }

        public virtual DbSet<EquipmentState> EquipmentStates { get; set; }

        public virtual DbSet<EquipmentStateHistory> EquipmentStateHistory { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        => optionsBuilder.UseNpgsql(ConnectionString);

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Equipment>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("equipment_pkey");

                entity.ToTable("equipment", "operation");

                entity.Property(e => e.Id)
                    .ValueGeneratedNever()
                    .HasColumnName("id");
                entity.Property(e => e.Model_Id).HasColumnName("equipment_model_id");
                entity.Property(e => e.Name).HasColumnName("name");

                entity.HasOne(d => d.EquipmentModel).WithMany(p => p.Equipment)
                    .HasForeignKey(d => d.Model_Id)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_equipment_model");
            });

            modelBuilder.Entity<EquipmentModel>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("equipment_model_pkey");

                entity.ToTable("equipment_model", "operation");

                entity.Property(e => e.Id)
                    .ValueGeneratedNever()
                    .HasColumnName("id");
                entity.Property(e => e.Name).HasColumnName("name");
            });

            modelBuilder.Entity<EquipmentModelStateHourEarn>(entity =>
            {
                entity
                    .HasNoKey()
                    .ToTable("equipment_model_state_hourly_earnings", "operation");

                entity.Property(e => e.Model_Id).HasColumnName("equipment_model_id");
                entity.Property(e => e.State_Id).HasColumnName("equipment_state_id");
                entity.Property(e => e.Value).HasColumnName("value");

                entity.HasOne(d => d.EquipmentModel).WithMany()
                    .HasForeignKey(d => d.Model_Id)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_equipment_model");

                entity.HasOne(d => d.EquipmentState).WithMany()
                    .HasForeignKey(d => d.State_Id)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_equipment_state");
            });

            modelBuilder.Entity<EquipmentPositHistory>(entity =>
            {
                entity
                    .HasNoKey()
                    .ToTable("equipment_position_history", "operation");

                entity.Property(e => e.Date)
                    .HasColumnType("timestamp without time zone")
                    .HasColumnName("date");
                entity.Property(e => e.Equipment_Id).HasColumnName("equipment_id");
                entity.Property(e => e.Latitude).HasColumnName("lat");
                entity.Property(e => e.Longitude).HasColumnName("lon");

                entity.HasOne(d => d.Equipment).WithMany()
                    .HasForeignKey(d => d.Equipment_Id)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_equipment");
            });

            modelBuilder.Entity<EquipmentState>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("equipment_state_pkey");

                entity.ToTable("equipment_state", "operation");

                entity.Property(e => e.Id)
                    .ValueGeneratedNever()
                    .HasColumnName("id");
                entity.Property(e => e.Color).HasColumnName("color");
                entity.Property(e => e.Name).HasColumnName("name");
            });

            modelBuilder.Entity<EquipmentStateHistory>(entity =>
            {
                entity
                    .HasNoKey()
                    .ToTable("equipment_state_history", "operation");

                entity.Property(e => e.Date)
                    .HasColumnType("timestamp without time zone")
                    .HasColumnName("date");
                entity.Property(e => e.Equipment_Id).HasColumnName("equipment_id");
                entity.Property(e => e.State_Id).HasColumnName("equipment_state_id");

                entity.HasOne(d => d.Equipment).WithMany()
                    .HasForeignKey(d => d.Equipment_Id)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_equipment");

                entity.HasOne(d => d.EquipmentState).WithMany()
                    .HasForeignKey(d => d.State_Id)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("fk_equipment_state");
            });

            OnModelCreatingPartial(modelBuilder);
        }
        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
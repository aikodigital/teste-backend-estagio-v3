﻿// <auto-generated />
using System;
using API_CRUD_Test.Data;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace API_CRUD_Test.Migrations
{
    [DbContext(typeof(EquipmentContext))]
    partial class EquipmentContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "7.0.4")
                .HasAnnotation("Relational:MaxIdentifierLength", 63);

            NpgsqlModelBuilderExtensions.UseIdentityByDefaultColumns(modelBuilder);

            modelBuilder.Entity("API_CRUD_Test.Classes.Equipment", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid");

                    b.Property<Guid>("EquipmentModelId")
                        .HasColumnType("uuid");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.ToTable("Equipment");
                });

            modelBuilder.Entity("API_CRUD_Test.Models.EquipmentModel", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.ToTable("EquipmentModel");
                });

            modelBuilder.Entity("API_CRUD_Test.Models.EquipmentModelStateHourlyEarnings", b =>
                {
                    b.Property<DateTime>("Date")
                        .HasColumnType("timestamp with time zone");

                    b.Property<Guid>("EquipmentId")
                        .HasColumnType("uuid");

                    b.Property<Guid>("EquipmentStateId")
                        .HasColumnType("uuid");

                    b.ToTable("EquipmentModelStateHourlyEarnings");
                });

            modelBuilder.Entity("API_CRUD_Test.Models.EquipmentPositionHistory", b =>
                {
                    b.Property<DateTime>("Date")
                        .HasColumnType("timestamp with time zone");

                    b.Property<Guid>("EquipmentId")
                        .HasColumnType("uuid");

                    b.Property<double>("Latitude")
                        .HasColumnType("double precision");

                    b.Property<double>("Longitude")
                        .HasColumnType("double precision");

                    b.ToTable("EquipmentPositionHistory");
                });

            modelBuilder.Entity("API_CRUD_Test.Models.EquipmentState", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid");

                    b.Property<string>("Color")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.ToTable("EquipmentState");
                });

            modelBuilder.Entity("API_CRUD_Test.Models.EquipmentStateHistory", b =>
                {
                    b.Property<DateTime>("Date")
                        .HasColumnType("timestamp with time zone");

                    b.Property<Guid>("EquipmentId")
                        .HasColumnType("uuid");

                    b.Property<Guid>("EquipmentStateId")
                        .HasColumnType("uuid");

                    b.ToTable("EquipmentStateHistory");
                });
#pragma warning restore 612, 618
        }
    }
}

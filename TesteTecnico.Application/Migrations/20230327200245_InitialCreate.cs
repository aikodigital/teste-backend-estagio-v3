using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace TesteTecnico.Application.Migrations
{
    /// <inheritdoc />
    public partial class InitialCreate : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "EquipmentModels",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    Name = table.Column<string>(type: "character varying(255)", maxLength: 255, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EquipmentModels", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "EquipmentStates",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    Name = table.Column<string>(type: "character varying(255)", maxLength: 255, nullable: false),
                    Color = table.Column<string>(type: "character varying(255)", maxLength: 255, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EquipmentStates", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Equipments",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    Name = table.Column<string>(type: "character varying(255)", maxLength: 255, nullable: false),
                    EquipmentModelId = table.Column<Guid>(type: "uuid", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Equipments", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Equipments_EquipmentModels_EquipmentModelId",
                        column: x => x.EquipmentModelId,
                        principalTable: "EquipmentModels",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "EquipmentModelStateHourlyEarnings",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    Value = table.Column<decimal>(type: "numeric", nullable: false),
                    EquipmentModelId = table.Column<Guid>(type: "uuid", nullable: false),
                    EquipmentStateId = table.Column<Guid>(type: "uuid", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EquipmentModelStateHourlyEarnings", x => x.Id);
                    table.ForeignKey(
                        name: "FK_EquipmentModelStateHourlyEarnings_EquipmentModels_Equipment~",
                        column: x => x.EquipmentModelId,
                        principalTable: "EquipmentModels",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_EquipmentModelStateHourlyEarnings_EquipmentStates_Equipment~",
                        column: x => x.EquipmentStateId,
                        principalTable: "EquipmentStates",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "EquipmentStateHistories",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    Date = table.Column<DateOnly>(type: "date", nullable: false),
                    EquipmentStateId = table.Column<Guid>(type: "uuid", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EquipmentStateHistories", x => x.Id);
                    table.ForeignKey(
                        name: "FK_EquipmentStateHistories_EquipmentStates_EquipmentStateId",
                        column: x => x.EquipmentStateId,
                        principalTable: "EquipmentStates",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "EquipmentPositionHistory",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    Date = table.Column<DateOnly>(type: "date", nullable: false),
                    Lat = table.Column<decimal>(type: "numeric", nullable: false),
                    Lon = table.Column<decimal>(type: "numeric", nullable: false),
                    EquipmentId = table.Column<Guid>(type: "uuid", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EquipmentPositionHistory", x => x.Id);
                    table.ForeignKey(
                        name: "FK_EquipmentPositionHistory_Equipments_EquipmentId",
                        column: x => x.EquipmentId,
                        principalTable: "Equipments",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_EquipmentModelStateHourlyEarnings_EquipmentModelId",
                table: "EquipmentModelStateHourlyEarnings",
                column: "EquipmentModelId");

            migrationBuilder.CreateIndex(
                name: "IX_EquipmentModelStateHourlyEarnings_EquipmentStateId",
                table: "EquipmentModelStateHourlyEarnings",
                column: "EquipmentStateId");

            migrationBuilder.CreateIndex(
                name: "IX_EquipmentPositionHistory_EquipmentId",
                table: "EquipmentPositionHistory",
                column: "EquipmentId");

            migrationBuilder.CreateIndex(
                name: "IX_Equipments_EquipmentModelId",
                table: "Equipments",
                column: "EquipmentModelId");

            migrationBuilder.CreateIndex(
                name: "IX_EquipmentStateHistories_EquipmentStateId",
                table: "EquipmentStateHistories",
                column: "EquipmentStateId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "EquipmentModelStateHourlyEarnings");

            migrationBuilder.DropTable(
                name: "EquipmentPositionHistory");

            migrationBuilder.DropTable(
                name: "EquipmentStateHistories");

            migrationBuilder.DropTable(
                name: "Equipments");

            migrationBuilder.DropTable(
                name: "EquipmentStates");

            migrationBuilder.DropTable(
                name: "EquipmentModels");
        }
    }
}

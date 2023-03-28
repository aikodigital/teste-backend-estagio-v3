using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace TesteTecnico.Application.Migrations
{
    /// <inheritdoc />
    public partial class FixEquipmentStateHistoryTable : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<Guid>(
                name: "EquipmentId",
                table: "EquipmentStateHistories",
                type: "uuid",
                nullable: false,
                defaultValue: new Guid("00000000-0000-0000-0000-000000000000"));

            migrationBuilder.CreateIndex(
                name: "IX_EquipmentStateHistories_EquipmentId",
                table: "EquipmentStateHistories",
                column: "EquipmentId");

            migrationBuilder.AddForeignKey(
                name: "FK_EquipmentStateHistories_Equipments_EquipmentId",
                table: "EquipmentStateHistories",
                column: "EquipmentId",
                principalTable: "Equipments",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_EquipmentStateHistories_Equipments_EquipmentId",
                table: "EquipmentStateHistories");

            migrationBuilder.DropIndex(
                name: "IX_EquipmentStateHistories_EquipmentId",
                table: "EquipmentStateHistories");

            migrationBuilder.DropColumn(
                name: "EquipmentId",
                table: "EquipmentStateHistories");
        }
    }
}

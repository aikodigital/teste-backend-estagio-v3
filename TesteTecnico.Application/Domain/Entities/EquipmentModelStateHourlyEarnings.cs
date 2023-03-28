using System.ComponentModel.DataAnnotations.Schema;

namespace TesteTecnico.Application.Domain.Entities;

public class EquipmentModelStateHourlyEarnings
{
    public Guid Id { get; set; } = Guid.NewGuid();
    public decimal Value { get; set; }

    [ForeignKey("EquipmentModelId")]
    public virtual EquipmentModel EquipmentModel { get; set; } = null!;

    [ForeignKey("EquipmentStateId")]
    public virtual EquipmentState EquipmentState { get; set; } = null!;
}
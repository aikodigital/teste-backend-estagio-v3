using System.ComponentModel.DataAnnotations.Schema;

namespace TesteTecnico.Application.Domain.Entities;

public class EquipmentModelStateHourlyEarning
{
    public Guid Id { get; set; } = Guid.NewGuid();
    public decimal Value { get; set; }

    [ForeignKey("EquipmentModelId")]
    public virtual EquipmentModel EquipmentModel { get; set; } = null!;
    public Guid EquipmentModelId { get; set; }

    [ForeignKey("EquipmentStateId")]
    public virtual EquipmentState EquipmentState { get; set; } = null!;
    public Guid EquipmentStateId { get; set; }
}
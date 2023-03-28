using System.ComponentModel.DataAnnotations.Schema;

namespace TesteTecnico.Application.Domain.Entities;

public class EquipmentStateHistory
{
    public Guid Id { get; set; } = Guid.NewGuid();
    public DateOnly Date { get; set; }

    [ForeignKey("EquipmentStateId")]
    public virtual EquipmentState EquipmentState { get; set; } = null!;
    public Guid EquipmentStateId { get; set; }

    [ForeignKey("EquipmentId")]
    public virtual Equipment Equipment { get; set; } = null!;
    public Guid EquipmentId { get; set; }
}
using System.ComponentModel.DataAnnotations.Schema;

namespace TesteTecnico.Application.Domain.Entities;

public class EquipmentPositionHistory
{
    public Guid Id { get; set; } = Guid.NewGuid();
    public DateOnly Date { get; set; }
    public decimal Lat { get; set; }
    public decimal Lon { get; set; }

    [ForeignKey("EquipmentId")]
    public virtual Equipment Equipment { get; set; } = null!;
    public Guid EquipmentId { get; set; }
}
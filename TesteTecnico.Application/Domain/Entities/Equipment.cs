using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace TesteTecnico.Application.Domain.Entities;

public class Equipment
{
    public Guid Id { get; set; } = Guid.NewGuid();

    [MaxLength(255)]
    public string Name { get; set; } = null!;

    [ForeignKey("EquipmentModelId")]
    public virtual EquipmentModel EquipmentModel { get; set; } = null!;
    public Guid EquipmentModelId { get; set; }
}
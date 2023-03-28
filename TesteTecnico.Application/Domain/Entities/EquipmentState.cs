using System.ComponentModel.DataAnnotations;

namespace TesteTecnico.Application.Domain.Entities;

public class EquipmentState
{
    public Guid Id { get; set; } = Guid.NewGuid();

    [MaxLength(255)]
    public string Name { get; set; } = null!;

    [MaxLength(255)]
    public string Color { get; set; } = null!;
}
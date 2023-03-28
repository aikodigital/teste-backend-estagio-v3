using System.ComponentModel.DataAnnotations;
namespace TesteTecnico.Application.Domain.Entities;

public class EquipmentModel
{
    public Guid Id { get; set; } = Guid.NewGuid();

    [MaxLength(255)]
    public string Name { get; set; } = null!;
}
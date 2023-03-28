using System.ComponentModel.DataAnnotations;

namespace EquipmentDomain.Models;

public partial class EquipmentModelStateHourlyEarning
{
    public Guid EquipmentModelId { get; set; }

    public Guid EquipmentStateId { get; set; }

    public float Value { get; set; }

    public virtual EquipmentModel EquipmentModel { get; set; } = null!;

    public virtual EquipmentState EquipmentState { get; set; } = null!;
}

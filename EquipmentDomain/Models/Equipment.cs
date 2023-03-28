using System;
using System.Collections.Generic;

namespace EquipmentDomain.Models;

public partial class Equipment
{
    public Guid Id { get; set; }

    public Guid EquipmentModelId { get; set; }

    public string Name { get; set; } = null!;

    public virtual EquipmentModel EquipmentModel { get; set; } = null!;
}

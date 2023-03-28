using System;
using System.Collections.Generic;

namespace EquipmentDomain.Models;

public partial class EquipmentModel
{
    public Guid Id { get; set; }

    public string Name { get; set; } = null!;

    public virtual ICollection<Equipment> Equipment { get; } = new List<Equipment>();
}

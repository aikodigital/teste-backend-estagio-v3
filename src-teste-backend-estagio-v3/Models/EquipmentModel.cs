using System;
using System.Collections.Generic;

namespace src_teste_backend_estagio_v3.Models;

public partial class EquipmentModel
{
    public Guid Id { get; set; }

    public string Name { get; set; } = null!;

    public virtual ICollection<Equipment> Equipment { get; } = new List<Equipment>();
}

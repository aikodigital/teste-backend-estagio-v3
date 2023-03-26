using System;
using System.Collections.Generic;

namespace src_teste_backend_estagio_v3.Models;

public partial class Equipment
{
    public Guid Id { get; set; }

    public Guid EquipmentModelId { get; set; }

    public string Name { get; set; } = null!;

    public virtual EquipmentModel EquipmentModel { get; set; } = null!;
}

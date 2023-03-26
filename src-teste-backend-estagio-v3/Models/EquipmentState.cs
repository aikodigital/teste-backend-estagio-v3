using System;
using System.Collections.Generic;

namespace src_teste_backend_estagio_v3.Models;

public partial class EquipmentState
{
    public Guid Id { get; set; }

    public string Name { get; set; } = null!;

    public string Color { get; set; } = null!;
}

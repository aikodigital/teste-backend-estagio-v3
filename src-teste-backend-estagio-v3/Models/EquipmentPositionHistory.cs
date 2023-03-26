using System;
using System.Collections.Generic;

namespace src_teste_backend_estagio_v3.Models;

public partial class EquipmentPositionHistory
{
    public Guid EquipmentId { get; set; }

    public DateTime Date { get; set; }

    public float Lat { get; set; }

    public float Lon { get; set; }

    public virtual Equipment Equipment { get; set; } = null!;
}

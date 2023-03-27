using System;
using System.Collections.Generic;

namespace WebApiAikoTeste.Models;

public partial class EquipmentStateHistory
{
    public Guid EquipmentId { get; set; }

    public DateTime Date { get; set; }

    public Guid EquipmentStateId { get; set; }

    public virtual Equipment Equipment { get; set; } = null!;

    public virtual EquipmentState EquipmentState { get; set; } = null!;
}

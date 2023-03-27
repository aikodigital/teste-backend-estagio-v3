using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace src_teste_backend_estagio_v3.Models;

public partial class EquipmentStateHistory
{
    public Guid EquipmentId { get; set; }

    public DateTime Date { get; set; }

    public Guid EquipmentStateId { get; set; }
    
    [JsonIgnore]
    public virtual Equipment Equipment { get; set; } = null!;
    
    [JsonIgnore]
    public virtual EquipmentState EquipmentState { get; set; } = null!;
}
using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace src_teste_backend_estagio_v3.Models;

public partial class EquipmentModelStateHourlyEarning
{
    public Guid EquipmentModelId { get; set; }

    public Guid EquipmentStateId { get; set; }

    public float Value { get; set; }

    [JsonIgnore]
    public virtual EquipmentModel EquipmentModel { get; set; } = null!;

    [JsonIgnore]
    public virtual EquipmentState EquipmentState { get; set; } = null!;
}
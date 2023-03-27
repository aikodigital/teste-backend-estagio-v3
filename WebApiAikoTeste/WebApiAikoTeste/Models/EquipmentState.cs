using System;
using System.Collections.Generic;

namespace WebApiAikoTeste.Models;

public partial class EquipmentState
{
    public Guid Id { get; set; }

    public string Name { get; set; } = null!;

    public string Color { get; set; } = null!;
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EquipmentDomain.DTOs
{
    public class EquipmentRequest
    {
        public string Name { get; set; }
        public Guid EquipmentModelId { get; set; }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreAPI.Models
{
    public class EquipmentModel
    {
        public Guid Id
        {get; set;}
        public string Name
        {get; set;} = null!;

        public virtual ICollection<Equipment> Equipment { get; } = new List<Equipment>();
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreAPI.Models
{
    public class EquipmentStateHistory
    {
        public Guid Equipment_Id
        {get; set;}
        public DateTime Date
        {get; set;}
        public Guid State_Id
        {get; set;}

        public virtual Equipment Equipment { get; set; } = null!;
        public virtual EquipmentState EquipmentState { get; set; } = null!;
    }
}
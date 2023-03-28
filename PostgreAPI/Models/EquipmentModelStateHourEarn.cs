using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreAPI.Models
{
    public class EquipmentModelStateHourEarn
    {
        public Guid Model_Id
        {get; set;}
        public Guid State_Id
        {get; set;}
        public float Value
        {get; set;}
        
        public virtual EquipmentModel EquipmentModel { get; set; } = null!;
        public virtual EquipmentState EquipmentState { get; set; } = null!;
    }
}
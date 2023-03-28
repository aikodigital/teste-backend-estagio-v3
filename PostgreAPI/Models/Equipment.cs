using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreAPI.Models
{
    public class Equipment
    {
        public Guid Id
        {get; set;}
        public string Name
        {get; set;} = null!;
        public Guid Model_Id
        {get; set;}

        public virtual EquipmentModel EquipmentModel { get; set; } = null!;
    }
}
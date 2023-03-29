using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreAPI.Models
{
    public class EquipmentState
    {
        public Guid Id
        {get; set;}
        public string Name 
        {get; set;} = null!;
        public string Color
        {get; set;} = null!;
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreAPI.Models
{
    public class EquipmentPositHistory
    {
        public Guid Equipment_Id
        {get; set;}
        public DateTime Date
        {get; set;}
        public float Latitude
        {get; set;}
        public float Longitude
        {get; set;}

        public virtual Equipment Equipment { get; set; } = null!;
    }
}
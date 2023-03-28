using EquipmentDomain.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EquipmentDomain.Interfaces.Repositories
{
    public interface IEquipmentRepository
    {
        public List<Equipment> GetEquipment();
        public Equipment GetEquipmentById(Guid id);
        public string AddNewEquipment(Equipment newEquipment);
        public string UpdateEquipment();
        public string DeleteEquipment(Equipment equipment);
    }
}

using EquipmentDomain.DTOs;
using EquipmentDomain.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EquipmentDomain.Interfaces.Services
{
    public interface IEquipmentService
    {
        public List<Equipment> GetEquipment();
        public Equipment GetEquipmentById(Guid id);
        public string AddNewEquipment(EquipmentRequest equipmentRequest);
        public string UpdateEquipment(EquipmentRequest equipmentRequest, Guid id);
        public string DeleteEquipment(Guid id);
    }
}

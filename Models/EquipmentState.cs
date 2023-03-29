using System.ComponentModel.DataAnnotations;

namespace API_CRUD_Test.Models
{
    public class EquipmentState
    {
        [Key]
        public Guid? Id { get; set; } 
        public string Name { get; set; }
        public string Color { get; set; }
    }
}

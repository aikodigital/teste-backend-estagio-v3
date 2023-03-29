using System.ComponentModel.DataAnnotations;

namespace API_CRUD_Test.Models
{
    public class EquipmentModel
    {
        [Key]
        public Guid Id { get; set; } //Chave Primária
        public string Name { get; set; }
    }
}

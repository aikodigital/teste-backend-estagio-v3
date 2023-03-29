using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace API_CRUD_Test.Models
{
    public class Equipment
    {
        [Key]
        public Guid Id { get; set; }
        public string Name { get; set; }

        [ForeignKey("EquipmentModel")]
        public Guid? EquipmentModelId { get; set; } //Essa é uma chave estrangeira
    }
}

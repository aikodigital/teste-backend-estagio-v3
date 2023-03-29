using System.ComponentModel.DataAnnotations.Schema;

namespace API_CRUD_Test.Models
{
    public class EquipmentModelStateHourlyEarnings
    {
       
        public float Value { get; set; }
        
        [ForeignKey("EquipmentState")]
        public Guid EquipmentStateId { get; set; } //chave estrangeira
        
        [ForeignKey("EquipmentModel")]
        public Guid EquipmentModelId { get; set; } //chave estrangeira

     
    }
}

using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace API_CRUD_Test.Models
{
    public class EquipmentStateHistory
    {
        [ForeignKey("Equipment")]
        public Guid EquipmentId { get; set; } //chave estrangeira

        public DateTime? Date { get; set; }

        [ForeignKey("EquipmentState")]
        public Guid EquipmentStateId { get; set; } //chave estrangeira
    }
}

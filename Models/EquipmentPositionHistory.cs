using System.ComponentModel.DataAnnotations.Schema;

namespace API_CRUD_Test.Models
{
    public class EquipmentPositionHistory
    {
        [ForeignKey("Equipment")]
        public Guid? EquipmentId { get; set; }
        public DateTime? Date { get; set; }
        public double? Lat { get; set; }
        public double? Lon { get; set; }
    }
}

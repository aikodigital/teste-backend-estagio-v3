namespace EquipmentDomain.DTOs
{
    public class EquipmentStateHistoryRequest
    {
        public Guid EquipmentId { get; set; }

        public DateTime Date { get; set; }

        public Guid EquipmentStateId { get; set; }
    }
}

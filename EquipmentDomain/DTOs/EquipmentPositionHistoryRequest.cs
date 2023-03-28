namespace EquipmentDomain.DTOs
{
    public class EquipmentPositionHistoryRequest
    {
        public Guid EquipmentId { get; set; }

        public float Lat { get; set; }

        public float Lon { get; set; }
    }
}

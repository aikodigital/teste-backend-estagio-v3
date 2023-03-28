using Newtonsoft.Json;

namespace EquipmentDomain.DTOs
{
    public class EquipmentPositionHistoryDataRequest
    {
        public Guid EquipmentId { get; set; }

        public DateTime Date { get; set; }

        public float Lat { get; set; }

        public float Lon { get; set; }

    }
}

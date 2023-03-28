namespace EquipmentDomain.DTOs
{
    public class EquipmentModelStateHourlyEarningRequest
    {
        public Guid EquipmentModelId { get; set; }

        public Guid EquipmentStateId { get; set; }

        public float Value { get; set; }
    }
}

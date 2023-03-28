using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PostgreAPI.Models;

namespace PostgreAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class EquipmentModelStateHourEarnController : ControllerBase
    {
        [HttpPost]
        [Route("InsertEquipmentModelStateHourEarn")]

        public bool InsertEquipmentModelStateHourEarn(float Value, Guid ModelId, Guid StateId)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.EquipmentModels.Where(x => x.Id == ModelId).FirstOrDefault() == null)
                  throw new Exception("EquipmentModelId é inválido!");

                if (context.EquipmentStates.Where(x => x.Id == StateId).FirstOrDefault() == null)
                    new Exception("EquipmentStateId é inválido!");


                string sql = @$" INSERT INTO operation.equipment_model_state_hourly_earnings(equipment_model_id, equipment_state_id, value)
	                                          VALUES ('{ModelId.ToString()}', '{StateId.ToString()}', {Value});";

                int rowsAffected = context.Database.ExecuteSqlRaw(sql);

                return (rowsAffected > 0);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpGet]
        [Route("GetEquipmentModelStateHourEarn")]
        public List<EquipmentModelStateHourEarn> GetEquipmentModelStateHouEarning()
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();
                List<EquipmentModelStateHourEarn> equipmentModelStateHourEarnList = context.EquipmentModelStateHourEarn.ToList();
                return equipmentModelStateHourEarnList;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        // TODO: Implementar update

        [HttpDelete]
        [Route("DeleteEquipmentModelStateHourEarn")]
        public bool DeleteEquipmentModelStateHourEarn(Guid EquipmentModelId, Guid EquipmentStatelId)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.EquipmentModels.Where(x => x.Id == EquipmentModelId).FirstOrDefault() == null)
                    throw new Exception("EquipmentModelId é inválido!");

                if (context.EquipmentStates.Where(x => x.Id == EquipmentStatelId).FirstOrDefault() == null)
                    throw new Exception("EquipmentStateId é inválido!");


                string sql = @$"DELETE FROM operation.equipment_model_state_hourly_earnings
                                 WHERE equipment_model_id = '{EquipmentModelId.ToString()}'AND equipment_state_id = '{EquipmentStatelId.ToString()}';";

                int rowsAffected = context.Database.ExecuteSqlRaw(sql);

                return (rowsAffected > 0);
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }
    }
}
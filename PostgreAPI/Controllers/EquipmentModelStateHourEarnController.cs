using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PostgreAPI.Models;

namespace PostgreAPI.Controllers
{
    [ApiController]
    public class EquipmentModelStateHourEarnController : ControllerBase
    {
        [HttpPost]
        [Route("/HourlyEarnings/Insert")]
        public bool InsertEquipmentModelStateHourEarn(float Value, Guid ModelId, Guid StateId)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.EquipmentModels.Where(x => x.Id == ModelId).FirstOrDefault() == null)
                    throw new Exception("EquipmentModelId é inválido!");

                if (context.EquipmentStates.Where(x => x.Id == StateId).FirstOrDefault() == null)
                    throw new Exception("EquipmentStateId é inválido!");


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
        [Route("/HourlyEarnings/Get")]
        public List<EquipmentModelStateHourEarn> GetEquipmentModelStateHourEarn()
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

        [HttpPut]
        [Route("/HourlyEarnings/Update")]
        public bool UpdateEquipmentModelStateHourEarn(Guid ModelId, Guid StateId, float Value)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.EquipmentModels.Where(x => x.Id == ModelId).FirstOrDefault() == null)
                    throw new Exception("EquipmentModelId é inválido!");

                if (context.EquipmentStates.Where(x => x.Id == StateId).FirstOrDefault() == null)
                    throw new Exception("EquipmentStateId é inválido!");

                string sql = @$"UPDATE operation.equipment_model_state_hourly_earnings SET value = {Value}
	                            WHERE equipment_model_id = '{ModelId.ToString()}' AND equipment_state_id = '{StateId.ToString()}';";

                int rowsAffected = context.Database.ExecuteSqlRaw(sql);
                return (rowsAffected > 0);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpDelete]
        [Route("/HourlyEarnings/Delete")]
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
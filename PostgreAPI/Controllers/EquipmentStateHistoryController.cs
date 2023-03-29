using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PostgreAPI.Models;

namespace PostgreAPI.Controllers
{
    [ApiController]
    public class EquipmentStateHistoryController : ControllerBase
    {

        [HttpPost]
        [Route("/EquipmentStateHistory/Insert")]
        public bool InsertEquipmentStateHistory(Guid EquipmentStateId, DateTime Date,Guid EquipmentId)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.Equipment.Where(x => x.Id == EquipmentId).FirstOrDefault() == null)
                    throw new Exception("EquipmentId é inválido!");

                if (context.EquipmentStates.Where(x => x.Id == EquipmentStateId).FirstOrDefault() == null)
                    throw new Exception("EquipmentStateId é inválido!");


                string sql = @$"INSERT INTO operation.equipment_state_history (equipment_id, date, equipment_state_id) 
	                            VALUES ('{EquipmentId.ToString()}', '{Date.ToString("yyyy-MM-dd HH:mm:ss")}', '{EquipmentStateId.ToString()}');";

                int rowsAffected= context.Database.ExecuteSqlRaw(sql);

                return (rowsAffected > 0);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpGet]
        [Route("/EquipmentStateHistory/Get")]
        public List<EquipmentStateHistory> GetStateHistory()
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();
                List<EquipmentStateHistory> equipmentStateHistory = context.EquipmentStateHistory.ToList();
                return equipmentStateHistory;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        
        [HttpDelete]
        [Route("/EquipmentStateHistory/Delete")]
        public bool DeleteEquipmentStateHistory(Guid EquipmentId,DateTime date)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.Equipment.Where(x => x.Id == EquipmentId).FirstOrDefault() == null)
                    throw new Exception("EquipmentId é inválido!");


                string sql = @$"DELETE FROM operation.equipment_state_history
                                   WHERE equipment_model_id = '{EquipmentId}','{date.ToString("yyyy-MM-dd HH:mm:ss")}';";

                int rowsAffected = context.Database.ExecuteSqlRaw(sql);

                return (rowsAffected > 0);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpGet]
        [Route("/EquipmentStateHistory/GetCurrent")]
        public List<EquipmentStateHistory> GetCurrentEquipmentState()
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                List<EquipmentStateHistory> listEquip = context.EquipmentStateHistory
                .FromSql(@$"WITH currentEquipmentStateHistory AS (
		                        SELECT 
			                        equipment_id, 
			                        date, 
			                        equipment_state_id, 
			                        row_number() over (PARTITION BY equipment_id ORDER BY date desc)as row_date
		                        FROM operation.equipment_state_history
	                        )
                        SELECT equipment_id, 
			                    date, 
			                    equipment_state_id
                        FROM currentEquipmentStateHistory
                        WHERE row_date = 1")
                .ToList();

                return listEquip;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
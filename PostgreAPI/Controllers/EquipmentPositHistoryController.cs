using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PostgreAPI.Models;

namespace PostgreAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class EquipmentPositHistoryController : ControllerBase
    {
        [HttpPost]
        [Route("InsertEquipmentPositHistory")]
        public bool InsertEquipmentPositHistory(Guid EquipmentId, DateTime dateTime, float lat, float lon)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.Equipment.Where(x => x.Id == EquipmentId).FirstOrDefault() == null)
                    throw new Exception("EquipmentId é inválido!");


                if (dateTime.ToString().Trim().Count() <= 0)
                    throw new Exception("DateTime é inválido!");


                if (lat.ToString().Trim().Count() <= 0)
                    throw new Exception("Lat é inválido!");


                if (lon.ToString().Trim().Count() <= 0)
                    throw new Exception("lon é inválido!");

                string sql = @$"INSERT INTO operation.equipment_position_history (equipment_id, date, lat, lon) 
	                            VALUES ('{EquipmentId.ToString()}', '{dateTime.ToString("yyyy-MM-dd HH:mm:ss")}',{lat},{lon});";

                int rowsAffected = context.Database.ExecuteSqlRaw(sql);

                return (rowsAffected > 0);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpGet]
        [Route("GetEquipmentPositHistory")]
        public List<EquipmentPositHistory> GetPositionHistory()
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();
                List<EquipmentPositHistory> equipmentPositHistory = context.EquipmentPositHistory.ToList();
                return equipmentPositHistory;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpDelete]
        [Route("DeleteEquipmentPositHistory")]
        public bool DeleteEquipmentPositHistory(Guid EquipmentId,DateTime date)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.Equipment.Where(x => x.Id == EquipmentId).FirstOrDefault() == null)
                    throw new Exception("EquipmentId é inválido!");

                string sql = @$"DELETE FROM operation.equipment_position_history
                                    WHERE equipment_id = '{EquipmentId}','{date.ToString("yyyy-MM-dd HH:mm:ss")}';";

                int rowsAffected = context.Database.ExecuteSqlRaw(sql);

                return (rowsAffected > 0); ;
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }

        // TODO: Implentar update

        [HttpGet]
        [Route("GetCurrentEquipmentPosition")]
        public List<EquipmentPositHistory> GetCurrentEquipmentEquipment()
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                List<EquipmentPositHistory> listEquip = context.EquipmentPositHistory
                .FromSql(@$"WITH currentEquipmentPositionHistory AS (
		                        SELECT 
			                        equipment_id, 
			                        date, 
			                        lat, 
                                    lon,
			                        row_number() over (PARTITION BY equipment_id ORDER BY date desc)as row_date
		                        FROM operation.equipment_position_history
	                        )
                        SELECT equipment_id, 
			                    date, 
			                    lat, 
                                lon
                        FROM currentEquipmentPositionHistory
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
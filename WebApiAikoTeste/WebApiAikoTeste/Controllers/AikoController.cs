using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using WebApiAikoTeste.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Hosting;
using Microsoft.EntityFrameworkCore.Query.SqlExpressions;
using Microsoft.EntityFrameworkCore.Metadata.Internal;
using System.Xml.Linq;
using System.Drawing;
using static Microsoft.EntityFrameworkCore.DbLoggerCategory;
using System;

namespace WebApiAikoTeste.Controllers
{

    [ApiController]
    [Route("[controller]")]

    public class AikoController : ControllerBase
    {
        [HttpGet]
        [Route("GetEquipment")]
        public List<Equipment> GetEquipment()
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

                List<Equipment> equipmentList = context.Equipment.ToList();
                return equipmentList;
            }
            catch (Exception ex)
            {
                throw ex;
            }


        }

        [HttpPost]
        [Route("insertEquipment")]

        public Guid insertEquipment(string Name, Guid EquipmentModelId)
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

                if (context.EquipmentModels.Where(x => x.Id == EquipmentModelId).FirstOrDefault() == null)
                    throw new Exception("EquipmentModelId é inválido!");

                if (Name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");


                Guid id = Guid.NewGuid();

                Equipment equipment = new Equipment();
                equipment.Name = Name;
                equipment.Id = id;
                equipment.EquipmentModelId = EquipmentModelId;

                context.Equipment.Add(equipment);
                context.SaveChanges();

                return id;
            }

            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPost]
        [Route("DeleteEquipment")]

        public bool DeleteEquipment(Guid id)
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

                Equipment equipment = context.Equipment.Where(x => x.Id == id).FirstOrDefault();

                if (equipment == null)
                    throw new Exception("EquipmentId é inválido!");

                context.Equipment.Remove(equipment);
                context.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPost]
        [Route("UpdateEquipment")]

        public bool UpdateEquipment(Guid id, String name, Guid ModelId)
        {
            try
            {

                AikoTesteContext context = new AikoTesteContext();


                if (context.EquipmentModels.Where(x => x.Id == ModelId).FirstOrDefault() == null)
                    throw new Exception("EquipmentModelId é inválido!");

                if (name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");

                Equipment equipment = context.Equipment.Where(x => x.Id == id).FirstOrDefault();

                if (equipment == null)
                    throw new Exception("EquipmentId é inválido!");

                    equipment.Name = name;
                    equipment.EquipmentModelId = ModelId;

                    context.Equipment.Attach(equipment);
                    context.Entry(equipment).State = EntityState.Modified;
                    context.SaveChanges();
                    return true;
            }
            catch (Exception ex)
            {
                throw ex;
            }
}

        [HttpGet]
        [Route("GetEquipmentState")]
        public List<EquipmentState> GetEquipmentState()
        {
            try
            {
                AikoTesteContext contextState = new AikoTesteContext();
                List<EquipmentState> equipmentStateList = contextState.EquipmentStates.ToList();
                return equipmentStateList;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPost]
        [Route("insertEquipmentState")]

        public Guid insertEquipmentState(string Name, string Color)
        {
            try
            {

                AikoTesteContext context = new AikoTesteContext();

               

                if (Name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");


                Guid id = Guid.NewGuid();

                EquipmentState equipmentState = context.EquipmentStates.Where(x => x.Color == Color).FirstOrDefault();

                if (equipmentState == null)
                    throw new Exception("EquipmentColor é inválido!");

                equipmentState.Name = Name;
                equipmentState.Id = id;
                equipmentState.Color = Color;

                context.EquipmentStates.Add(equipmentState);
                context.SaveChanges();

                return id;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }


        [HttpPost]
        [Route("deleteEquipmentState")]

        public bool DeleteEquipmentState(Guid id)
        {
            try
            {
               

                AikoTesteContext context = new AikoTesteContext();


                EquipmentState equipmentState = context.EquipmentStates.Where(x => x.Id == id).FirstOrDefault();

                if (equipmentState == null)
                    throw new Exception("EquipmentId é inválido!");

                context.EquipmentStates.Remove(equipmentState);
                context.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }

        [HttpPost]
        [Route("UpdateEquipmentState")]

        public bool UpdateEquipmentState(Guid id, string name, string color)
        {
            try
            {

                AikoTesteContext context = new AikoTesteContext();

               

                if (context.EquipmentStates.Where(x => x.Color == color).FirstOrDefault() == null)
                    throw new Exception("EquipmentStateColor é inválido!");

                if (name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");

                EquipmentState equipmentState = context.EquipmentStates.Where(x => x.Id == id).FirstOrDefault();

                if (equipmentState == null)
                    throw new Exception("EquipmentId é inválido!");

                equipmentState.Name = name;
                equipmentState.Color = color;

                context.EquipmentStates.Attach(equipmentState);
                context.Entry(equipmentState).State = EntityState.Modified;
                context.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }

        [HttpGet]
        [Route("GetEquipmentModel")]
        public List<EquipmentModel> GetEquipmentModel()
        {
            try
            {
                AikoTesteContext contextModel = new AikoTesteContext();
                List<EquipmentModel> equipmentModelList = contextModel.EquipmentModels.ToList();
                return equipmentModelList;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPost]
        [Route("insertEquipmentModel")]

        public Guid insertEquipmentModel(string Name)
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();


                if (Name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");

                Guid id = Guid.NewGuid();

                EquipmentModel equipmentModel = new EquipmentModel();
                equipmentModel.Name = Name;
                equipmentModel.Id = id;


                context.EquipmentModels.Add(equipmentModel);
                context.SaveChanges();

                return id;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPost]
        [Route("deleteEquipmentModel")]

        public bool DeleteEquipmentModel(Guid id)
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

                EquipmentModel equipmentModel = context.EquipmentModels.Where(x => x.Id == id).FirstOrDefault();

                if (equipmentModel == null)
                    throw new Exception("EquipmentId é inválido!");
                

                context.EquipmentModels.Remove(equipmentModel);
                context.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }

        [HttpPost]
        [Route("UpdateEquipmentModel")]

        public bool UpdateEquipmentModel(Guid id, string name)
        {
            try
            {

                AikoTesteContext context = new AikoTesteContext();

                if (name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");

                EquipmentModel equipmentModel = context.EquipmentModels.Where(x => x.Id == id).FirstOrDefault();

                if (equipmentModel == null)
                    throw new Exception("EquipmentId é inválido!");

                equipmentModel.Name = name;


                context.EquipmentModels.Attach(equipmentModel);
                context.Entry(equipmentModel).State = EntityState.Modified;
                context.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }

        [HttpGet]
        [Route("GetEquipmentModelStateHourlyEarning")]
        public List<EquipmentModelStateHourlyEarning> GetEquipmentModelStateHourlyEarning()
        {
            try
            {

                AikoTesteContext context = new AikoTesteContext();
                List<EquipmentModelStateHourlyEarning> equipmentModelStateHourlyEarningList = context.EquipmentModelStateHourlyEarnings.ToList();
                return equipmentModelStateHourlyEarningList;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPost]
        [Route("insertEquipmentModelStateHourlyEarning")]

        public bool insertEquipmentModelStateHourlyEarning(float Value, Guid ModelId, Guid StateId)
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

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

        [HttpPost]
        [Route("DeleteEquipmentModelStateHourlyEarning")]
        public bool DeleteEquipmentModelStateHourlyEarning(Guid EquipmentModelId, Guid EquipmentStatelId)
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

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


        [HttpGet]
        [Route("GetEquipmentPositionHistory")]
        public List<EquipmentPositionHistory> GetPositionHistory()
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();
                List<EquipmentPositionHistory> equipmentPositionHistory = context.EquipmentPositionHistories.ToList();
                return equipmentPositionHistory;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPost]
        [Route("insertEquipmentPositionHistory")]

        public bool insertEquipmentPositionHistory(Guid EquipmentId, DateTime dateTime, float lat, float lon)
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

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

        [HttpPost]
        [Route("DeleteEquipmentPositionHistory")]
        public bool DeleteEquipmentPositionHistory(Guid EquipmentId,DateTime date)
        {
            try
            {
                

                AikoTesteContext context = new AikoTesteContext();

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

        [HttpGet]
        [Route("GetEquipmentStateHistory")]
        public List<EquipmentStateHistory> GetStateHistory()
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();
                List<EquipmentStateHistory> equipmentStateHistory = context.EquipmentStateHistories.ToList();
                return equipmentStateHistory;
            }
            catch (Exception ex)
            {
                throw ex;
            }


        }

        [HttpPost]
        [Route("insertEquipmentStateHistory")]

        public bool insertEquipmentStateHistory(Guid EquipmentStateId, DateTime Date,Guid EquipmentId)
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

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

        [HttpPost]
        [Route("DeleteEquipmentStateHistory")]
        public bool DeleteEquipmentStateHistory(Guid EquipmentId,DateTime date)
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();


                if (context.Equipment.Where(x => x.Id == EquipmentId).FirstOrDefault() == null)
                    throw new Exception("EquipmentId é inválido!");


                string sql = @$"DELETE FROM operation.equipment_state_history
                                   WHERE equipment_model_id = '{EquipmentId}','{date.ToString("yyyy-MM-dd HH:mm:ss")}';";

                int rowsAffected = context.Database.ExecuteSqlRaw(sql);

                return (rowsAffected > 0); ;
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }


        [HttpPost]
        [Route("GetCurrentStateEquipment")]

        public List<EquipmentStateHistory> GetCurrentStateEquipment()
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

                List<EquipmentStateHistory> listEquip = context.EquipmentStateHistories
                .FromSql(@$"WITH currentEquipmentStateHistory AS (
		                        SELECT 
			                        equipment_id, 
			                        date, 
			                        equipment_state_id, 
			                        row_number() over (PARTITION BY equipment_id ORDER BY date desc)as rownum
		                        FROM operation.equipment_state_history
	                        )
                        select equipment_id, 
			                        date, 
			                        equipment_state_id
                        from currentEquipmentStateHistory
                        where rownum = 1")
                .ToList();


                return listEquip;
            }
            catch (Exception ex)
            {
                throw ex;
            }



        }

        [HttpPost]
        [Route("GetCurrentPositionEquipment")]
        public List<EquipmentPositionHistory> GetCurrentPositionEquipment()
        {
            try
            {
                AikoTesteContext context = new AikoTesteContext();

                List<EquipmentPositionHistory> listEquip = context.EquipmentPositionHistories
                .FromSql(@$"WITH currentEquipmentPositionHistory AS (
		                        SELECT 
			                        equipment_id, 
			                        date, 
			                        lat, 
                                    lon,
			                        row_number() over (PARTITION BY equipment_id ORDER BY date desc)as rownum
		                        FROM operation.equipment_position_history
	                        )
                        select equipment_id, 
			                        date, 
			                        lat, 
                                    lon
                        from currentEquipmentPositionHistory
                        where rownum = 1")
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

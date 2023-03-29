using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PostgreAPI.Models;

namespace PostgreAPI.Controllers
{
    [ApiController]
    public class EquipmentController : ControllerBase
    {
        [HttpPost]
        [Route("/equipment/Insert")]
        public Guid InsertEquipment(string Name, Guid Model_Id)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.EquipmentModels.Where(x => x.Id == Model_Id).FirstOrDefault() == null)
                    throw new Exception("Model_Id é inválido!");

                if (Name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");


                Guid id = Guid.NewGuid();

                Equipment equipment = new Equipment();
                equipment.Name = Name;
                equipment.Id = id;
                equipment.Model_Id = Model_Id;

                context.Equipment.Add(equipment);
                context.SaveChanges();

                return id;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpGet]
        [Route("/equipment/Get")]
        public List<Equipment> GetEquipment()
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                List<Equipment> equipmentList = context.Equipment.ToList();
                return equipmentList;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPut]
        [Route("/equipment/Update")]
        public bool UpdateEquipment(Guid id, String name, Guid ModelId)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.EquipmentModels.Where(x => x.Id == ModelId).FirstOrDefault() == null)
                    throw new Exception("Model_Id é inválido!");

                if (name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");

                Equipment? equipment = context.Equipment.Where(x => x.Id == id).FirstOrDefault();

                if (equipment == null)
                    throw new Exception("EquipmentId é inválido!");

                    equipment.Name = name;
                    equipment.Model_Id = ModelId;

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

        [HttpDelete]
        [Route("/equipment/Delete")]
        public bool DeleteEquipment(Guid id)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                Equipment? equipment = context.Equipment.Where(x => x.Id == id).FirstOrDefault();

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
    }
}
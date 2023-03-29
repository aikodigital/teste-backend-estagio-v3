using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PostgreAPI.Models;

namespace PostgreAPI.Controllers
{
    [ApiController]
    public class EquipmentStateController : ControllerBase
    {
        [HttpPost]
        [Route("/EquipmentState/Insert")]
        public Guid insertEquipmentState(string name, string color)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");

                Guid id = Guid.NewGuid();

                EquipmentState? equipmentState = context.EquipmentStates.Where(x => x.Color == color).FirstOrDefault();

                if (equipmentState == null)
                    throw new Exception("EquipmentColor é inválido!");

                equipmentState.Name = name;
                equipmentState.Id = id;
                equipmentState.Color = color;

                context.EquipmentStates.Add(equipmentState);
                context.SaveChanges();

                return id;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpGet]
        [Route("/EquipmentState/Get")]
        public List<EquipmentState> GetEquipmentState()
        {
            try
            {
                AikoAPIContext contextState = new AikoAPIContext();
                List<EquipmentState> equipmentStateList = contextState.EquipmentStates.ToList();
                return equipmentStateList;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPut]
        [Route("/EquipmentState/Update")]
        public bool UpdateEquipmentState(Guid id, string name, string color)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (context.EquipmentStates.Where(x => x.Color == color).FirstOrDefault() == null)
                    throw new Exception("EquipmentStateColor é inválido!");

                if (name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");

                EquipmentState? equipmentState = context.EquipmentStates.Where(x => x.Id == id).FirstOrDefault();

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

        [HttpDelete]
        [Route("/EquipmentState/Delete")]
        public bool DeleteEquipmentState(Guid id)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                EquipmentState? equipmentState = context.EquipmentStates.Where(x => x.Id == id).FirstOrDefault();

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
    }
}
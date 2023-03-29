using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PostgreAPI.Models;

namespace PostgreAPI.Controllers
{
    [ApiController]
    public class EquipmentModelController : ControllerBase
    {
        [HttpPost]
        [Route("/EquipmentModel/Insert")]
        public Guid InsertEquipmentModel(string name)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();


                if (name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");

                Guid id = Guid.NewGuid();

                EquipmentModel equipmentModel = new EquipmentModel();
                equipmentModel.Name = name;
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

        [HttpGet]
        [Route("/EquipmentModel/Get")]
        public List<EquipmentModel> GetEquipmentModel()
        {
            try
            {
                AikoAPIContext contextModel = new AikoAPIContext();
                List<EquipmentModel> equipmentModelList = contextModel.EquipmentModels.ToList();
                return equipmentModelList;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }


        [HttpPut]
        [Route("/EquipmentModel/Update")]
        public bool UpdateEquipmentModel(Guid id, string name)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                if (name.Trim().Count() <= 0)
                    throw new Exception("Name é inválido!");

                EquipmentModel? equipmentModel = context.EquipmentModels.Where(x => x.Id == id).FirstOrDefault();

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

        [HttpDelete]
        [Route("/EquipmentModel/Delete")]
        public bool DeleteEquipmentModel(Guid id)
        {
            try
            {
                AikoAPIContext context = new AikoAPIContext();

                EquipmentModel? equipmentModel = context.EquipmentModels.Where(x => x.Id == id).FirstOrDefault();

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
    }
}
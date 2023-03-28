using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Models;

namespace EquipmentInfra.Repositories
{
    public class EquipmentModelRepository : IEquipmentModelRepository
    {
        private readonly DbequipamentoContext _dbequipamentoContext;

        public EquipmentModelRepository(DbequipamentoContext dbequipamentoContext)
        {
            _dbequipamentoContext = dbequipamentoContext;
        }

        public List<EquipmentModel> GetEquipmentModel()
        {
            return (from E in _dbequipamentoContext.EquipmentModels
                    select E).ToList();
        }

        public EquipmentModel GetEquipmentModelById(Guid id)
        {
            return (from E in _dbequipamentoContext.EquipmentModels
                    where E.Id == id
                    select E).FirstOrDefault();
        }

        public string AddNewEquipmentModel(EquipmentModel newEquipment)
        {
            _dbequipamentoContext.Add(newEquipment);
            _dbequipamentoContext.SaveChanges();

            return "Modelo de equipamento adicionado com sucesso.";
        }

        public string UpdateEquipmentModel()
        {
            _dbequipamentoContext.SaveChanges();

            return "Modelo de equipamento atualizado com sucesso.";
        }

        public string DeleteEquipmentModel(EquipmentModel equipment)
        {
            _dbequipamentoContext.Remove(equipment);
            _dbequipamentoContext.SaveChanges();

            return "Modelo de equipamento excluído com sucesso.";
        }
    }
}

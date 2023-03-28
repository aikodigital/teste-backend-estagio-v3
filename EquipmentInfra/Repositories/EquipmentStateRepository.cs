using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Models;

namespace EquipmentInfra.Repositories
{
    public class EquipmentStateRepository : IEquipmentStateRepository
    {
        private readonly DbequipamentoContext _dbequipamentoContext;

        public EquipmentStateRepository(DbequipamentoContext dbequipamentoContext)
        {
            _dbequipamentoContext = dbequipamentoContext;
        }

        public List<EquipmentState> GetEquipmentState()
        {
           return (from E in _dbequipamentoContext.EquipmentStates
                  select E).ToList();
        }

        public EquipmentState GetEquipmentStateById(Guid id)
        {
            return (from E in _dbequipamentoContext.EquipmentStates
                    where E.Id == id
                    select E).FirstOrDefault();
        }

        public string AddNewEquipmentState(EquipmentState newEquipment)
        {
            _dbequipamentoContext.Add(newEquipment);
            _dbequipamentoContext.SaveChanges();

            return "Estado de equipamento adicionado com sucesso.";
        }

        public string UpdateEquipmentState()
        {
            _dbequipamentoContext.SaveChanges();

            return "Estado de equipamento atualizado com sucesso.";
        }

        public string DeleteEquipmentState(EquipmentState equipment)
        {
            _dbequipamentoContext.Remove(equipment);
            _dbequipamentoContext.SaveChanges();

            return "Estado de equipamento excluído com sucesso.";
        }
    }
}

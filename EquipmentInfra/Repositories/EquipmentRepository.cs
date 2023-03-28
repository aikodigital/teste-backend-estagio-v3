using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Models;

namespace EquipmentInfra.Repositories
{
    public class EquipmentRepository : IEquipmentRepository
    {
        private readonly DbequipamentoContext _dbequipamentoContext;

        public EquipmentRepository(DbequipamentoContext dbequipamentoContext)
        {
            _dbequipamentoContext = dbequipamentoContext;
        }

        public List<Equipment> GetEquipment()
        {
            return (from E in _dbequipamentoContext.Equipment
                   select E).ToList();
        }

        public Equipment GetEquipmentById(Guid id)
        {
            return (from E in _dbequipamentoContext.Equipment
                    where E.Id == id
                    select E).FirstOrDefault();
        }

        public string AddNewEquipment(Equipment newEquipment)
        {
            _dbequipamentoContext.Add(newEquipment);
            _dbequipamentoContext.SaveChanges();

            return "Equipamento adicionado com sucesso!";
        }

        public string UpdateEquipment()
        {
            _dbequipamentoContext.SaveChanges();

            return "Equipamento atualizado com sucesso!";
        }

        public string DeleteEquipment(Equipment equipment)
        {
            _dbequipamentoContext.Remove(equipment);
            _dbequipamentoContext.SaveChanges();

            return "Equipamento excluído com sucesso!";
        }
    }
}

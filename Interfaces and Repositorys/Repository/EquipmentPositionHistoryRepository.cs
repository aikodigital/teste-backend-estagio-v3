using API_CRUD_Test.Data;
using API_CRUD_Test.Models;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.EntityFrameworkCore;
using System.Data;

namespace API_CRUD_Test.Repository
{
    public class EquipmentPositionHistoryRepository : IEquipmentPositionHistory
    {
        private readonly EquipmentContext _equipmentContext;

        public EquipmentPositionHistoryRepository(EquipmentContext equipmentContext)
        {
            _equipmentContext = equipmentContext;
        }
        public EquipmentPositionHistory Add(EquipmentPositionHistory equipment)
        {
            // Verificação se o Modelo e o Estado estão preenchidos
            if (equipment.EquipmentId == Guid.Empty || equipment.Date == DateTime.MinValue || equipment.Lat == null || equipment.Lon == null)
            {
                throw new Exception("O preenchimento de todos os campos é obrigatório");
            }else
            {
                try
                {
                    equipment.Date = equipment.Date.Value.ToUniversalTime();
                    // Adiciona o objeto ao contexto
                    _equipmentContext.EquipmentPositionHistory.Add(equipment);
                    //Salva as mudanças
                    _equipmentContext.SaveChanges();
                    return equipment;
                }
                catch (DBConcurrencyException)
                {
                    throw new Exception("A atualização falhou devido à uma falha de concorrência, atualize seus dados e tente novamente");
                }
                catch (DbUpdateException)
                {
                    throw new Exception("Ocorreu um erro ao atualizar o banco de dados");
                }

            }
        }

        public EquipmentPositionHistory Edit(EquipmentPositionHistory equipment)
        {
            if (equipment == null)
            {
                throw new ArgumentNullException("O valor recebido é nulo.");
            }

            // Verifica se o equipamento e o estado existem no banco de dados
            var equipmentExists = _equipmentContext.Equipment.Any(e => e.Id == equipment.EquipmentId);
            if (!equipmentExists)
            {
                throw new Exception("O equipamento com o Id {equipment.EquipmentId} não existe no banco de dados");
            }

            // Busca o Equipamento a ser atualizado no Banco
            EquipmentPositionHistory equipmentToUpdate = _equipmentContext.EquipmentPositionHistory.Find(equipment.EquipmentId, equipment.Date);
            if (equipmentToUpdate == null) throw new Exception($"Não há equipamentos com o Id {equipment.EquipmentId} na data: {equipment.Date}");
            else
            {
                //Try Catch para checagem de exceptions
                try
                {
                    if (equipment.Date != DateTime.MinValue && equipment.Date.HasValue)
                    {
                        equipmentToUpdate.Date = equipment.Date.Value.ToUniversalTime();
                    }
                    if (equipment.Lat.HasValue)
                    {
                        equipmentToUpdate.Lat = equipment.Lat;
                    }
                    if (equipment.Lon.HasValue)
                    {
                        equipmentToUpdate.Lon = equipment.Lon;
                    }
                     _equipmentContext.EquipmentPositionHistory.Update(equipmentToUpdate);
                     _equipmentContext.SaveChanges();
                     return equipmentToUpdate;
                }
                catch (FormatException)
                {
                    throw new Exception("O valor inserido para a data não é válida, o formato correto é AAAA-MM-DD HH:MM:SS");
                }
            }
        }

        public EquipmentPositionHistory Remove(Guid equipId, DateTime date)
        {
            date = date.ToUniversalTime().AddHours(-3);
            //Busca do elemento a ser excluido pelo id e data.
            var equipment = _equipmentContext.EquipmentPositionHistory.FirstOrDefault(e => e.EquipmentId == equipId && e.Date == date);

            //Verifica a existência do modelo de equipamento
            if (equipment != null)
            {
                    //Remove o objeto
                    _equipmentContext.EquipmentPositionHistory.Remove(equipment);
                    //Salva
                    _equipmentContext.SaveChanges();
                    return equipment;
            }
            else
            {
                //Caso não encontre o elemento especificado.
                throw new Exception("O equipamento com a ID informada na data informada não possui registro de posição");
            }
        }

        public List<EquipmentPositionHistory> GetAllEquipmentPositionHistory()
        {
            return _equipmentContext.EquipmentPositionHistory.ToList();
        }

        public EquipmentPositionHistory CurrentPosition(Guid id)
        {
            if (id == Guid.Empty)
            {
                throw new Exception("Favor inserir algum valor!");
            }
            else
            {
                //Busca do elemento a ser excluido pelo id e data em ordem descendente a partir da data.
                var equipment = _equipmentContext.EquipmentPositionHistory.OrderByDescending(e => e.Date).FirstOrDefault(e => e.EquipmentId == id); ; 
                if (equipment != null)
                {
                    return equipment;
                }
                else
                {
                    throw new Exception ($"Não foi encontrado nenhuma posição para o equipamento com ID: {id}.");
                }
            }
        }
    }
}

using API_CRUD_Test.Data;
using API_CRUD_Test.Models;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.EntityFrameworkCore;
using System.Data;
using System.Globalization;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace API_CRUD_Test.Repository
{
    public class EquipmentStateHistoryRepository : IEquipmentStateHistory
    {
        private readonly EquipmentContext _equipmentContext;

        public EquipmentStateHistoryRepository(EquipmentContext equipmentContext)
        {
            _equipmentContext = equipmentContext;
        }

        public EquipmentStateHistory Add(EquipmentStateHistory equipment)
        {

            //Verificação se o Modelo e o Estado estão preenchidos
            if (equipment.EquipmentId == Guid.Empty || equipment.EquipmentStateId == Guid.Empty)
            {
                throw new Exception("A inserção do ID do Equipamento e do seu Estado é obrigatória");
            }else if (equipment.Date == null || equipment.Date == DateTime.MinValue)
            {
                throw new Exception("A inserção da Data em que o equipamento declarou estar em tal estado é obrigatória");
            }
            else
            {
                try
                {
                    equipment.Date = equipment.Date.Value.ToUniversalTime();
                    // Adiciona o objeto ao contexto
                    _equipmentContext.EquipmentStateHistory.Add(equipment);
                    //Salva as mudanças
                    _equipmentContext.SaveChanges();
                    return equipment;
                }
                catch (DbUpdateConcurrencyException)
                {
                    throw new Exception("A atualização falhou devido à uma falha de concorrência, atualize seus dados e tente novamente");
                }
                catch (DbUpdateException)
                {
                    throw new Exception("Ocorreu um erro ao atualizar o banco de dados");
                }

            }
        }

        public EquipmentStateHistory Edit(EquipmentStateHistory equipment)
        {
            if (equipment == null)
            {
                throw new ArgumentNullException("O valor recebido é nulo.");
            }

            // Verifica se o equipamento e o estado existem no banco de dados
            var equipmentExists = _equipmentContext.Equipment.Any(e => e.Id == equipment.EquipmentId);
            var stateExists = _equipmentContext.EquipmentState.Any(s => s.Id == equipment.EquipmentStateId);
            if (!equipmentExists || !stateExists)
            {
                throw new Exception($"O equipamento com o Id {equipment.EquipmentId} ou o estado com o Id {equipment.EquipmentStateId} não existe no banco de dados");
            }

            // Busca o Equipamento a ser atualizado no Banco
            EquipmentStateHistory equipmentToUpdate = _equipmentContext.EquipmentStateHistory.Find(equipment.EquipmentId, equipment.EquipmentStateId);
            if (equipmentToUpdate == null) throw new Exception($"Não há equipamentos com o Id {equipment.EquipmentId} no Estado {equipment.EquipmentStateId}");
            {
                //Try Catch para checagem de exceptions
                try
                {
                    if (equipment.Date != DateTime.MinValue)
                    {
                        equipmentToUpdate.Date = equipment.Date.Value.ToUniversalTime();
                        _equipmentContext.EquipmentStateHistory.Update(equipmentToUpdate);
                        _equipmentContext.SaveChanges();
                        return equipmentToUpdate;
                    }
                    else
                    {
                        throw new Exception("Favor inserir algum valor no campo de data");
                    }
                }catch (FormatException)
                    {
                        throw new Exception("O valor inserido para a data não é válida, o formato correto é AAAA-MM-DD HH:MM:SS");
                    }
                }
            }

        public EquipmentStateHistory Remove(Guid equipId, DateTime date)
        {
            //Busca do elemento a ser excluido pelo id e data.
            date = date.ToUniversalTime().AddHours(-3);
            var equipment = _equipmentContext.EquipmentStateHistory.FirstOrDefault(e => e.EquipmentId == equipId && e.Date == date);

            //Verifica a existência do modelo de equipamento
            if (equipment != null)
            {
                try
                {

                    //Remove o objeto
                    _equipmentContext.EquipmentStateHistory.Remove(equipment);
                    //Salva
                    _equipmentContext.SaveChanges();
                    return equipment;
                }
                catch (DbUpdateException)
                {
                    throw new Exception("As informações estão sendo utilizadas em outro lugar. Feche o outro processo e tente novamente.");
                }

            }
            else
            {
                //Caso não encontre o elemento especificado.
                throw new Exception($"O equipamento com ID {equipId} na data {date} não foi encontrado no banco de dados!\n" +
                    $"Lembre-se que as datas precisam ser inseridas no formato UTC YYYY-MM-DD HH:MM:ss");
            }
        }

        public List<EquipmentStateHistory> GetAllEquipmentStateHistory()
        {
            return _equipmentContext.EquipmentStateHistory.ToList();
        }
        public EquipmentStateHistory CurrentState(Guid id)
        {
            if (id == Guid.Empty)
            {
                throw new Exception("Favor inserir algum valor!");
            }
            else
            {
                //Busca do elemento a ser excluido pelo id e data em ordem descendente a partir da data.
                var equipment = _equipmentContext.EquipmentStateHistory.OrderByDescending(e => e.Date).FirstOrDefault(e => e.EquipmentId == id); ;
                if (equipment != null)
                {
                    return equipment;
                }
                else
                {
                    throw new Exception($"Não foi encontrado nenhuma posição para o equipamento com ID: {id}.");
                }
            }
        }
    }
}

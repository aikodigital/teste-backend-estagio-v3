using API_CRUD_Test.Data;
using API_CRUD_Test.Models;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.EntityFrameworkCore;
using System.Data;

namespace API_CRUD_Test.Repository
{
    public class EquipmentRepository : IEquipment
    {
        private readonly EquipmentContext _equipmentContext;
        public EquipmentRepository(EquipmentContext equipmentContext)
        {
            _equipmentContext = equipmentContext;
        }
        //----------//
        // Retorna listagem de todos os equipamentos
        //  Listagem para o View
        public List<Equipment> GetAllEquipment()
        {
            return _equipmentContext.Equipment.ToList();
        }

        //Adiciona equipamento
        public Equipment Add(Equipment equipment)
        {

            //Verificação se nome está vazio
                if (equipment.Name == null)
            {
                throw new Exception("A inserção do nome é obrigatória!");
            } // Verifica se modelo está vazio
                else if (equipment.EquipmentModelId == null)
            {
                throw new Exception("A inserção de um modelo é obrigatória");
            }    
              

            //Verifica a já existência de um equipament com o Id provido.
            if (_equipmentContext.Equipment.Any(findEquipment => findEquipment.Id == equipment.Id)) //Se qualquer elemento for = equipment.Id retorna true 
            {
                //Caso já exista, retorna o erro
                throw new Exception("Já existe um equipamento com o ID informado, tente outro!");
            }
            else
            {
                try
                {
                    // Adiciona o objeto ao contexto
                    _equipmentContext.Equipment.Add(equipment);
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
        public Equipment Remove(Guid id)
        {
            //Busca do elemento a ser excluido pelo id.
            var equipment = _equipmentContext.Equipment.Find(id);
            //Verifica a existência do equipamento
            if (equipment != null)
            {
                //Remove o objeto
                _equipmentContext.Equipment.Remove(equipment);
                //Salva
                _equipmentContext.SaveChanges();
                return equipment;
            }
            else
            {
                //Caso não encontre o elemento especificado.
                throw new Exception("O equipamento com o ID informado não foi encontrado");
            }


        }
        public Equipment Edit(Equipment equipment)
        {
            if (equipment == null)
            {
                throw new ArgumentNullException(nameof(equipment), "O equipamento recebido é nulo.");
            }
            // Busca o Equipamento a ser atualizado no Banco
            Equipment equipmentToUpdate = _equipmentContext.Equipment.Find(equipment.Id);
            if (equipmentToUpdate == null) throw new Exception($"Não há itens com o Id {equipment.Id}");
            else
            {
                //Checagem sem os campos foram preenchidos, caso não, mantém-se o valor anterior.
                if (equipmentToUpdate.Name != null)
                {
                    equipmentToUpdate.Name = equipment.Name;
                }

                if (equipmentToUpdate.EquipmentModelId.HasValue && equipment.EquipmentModelId.HasValue)
                {
                    //Verificações para lançar Exceptions caso necessário
                    try
                    {
                        if (_equipmentContext.Equipment.Find(equipment.EquipmentModelId) == null)
                        {
                            equipmentToUpdate.EquipmentModelId = equipment.EquipmentModelId.Value;
                        }
                        else
                        {
                            throw new Exception($"Não existe modelo de equipamento com o Id {equipment.EquipmentModelId}!");
                        }
                    }
                    catch (FormatException)
                    {
                        throw new Exception("O valor inserido para o modelo do equipamento é inválido!");
                    }
                }
            }
            //Atualiza equipamentos
            _equipmentContext.Equipment.Update(equipmentToUpdate);
            _equipmentContext.SaveChanges();
            return equipmentToUpdate;

        }


    }
}

using API_CRUD_Test.Data;
using API_CRUD_Test.Models;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.EntityFrameworkCore;
using System.Data;

namespace API_CRUD_Test.Repository
{
    public class EquipmentStateRepository : IEquipmentState
    {
        private readonly EquipmentContext _equipmentContext;
        public EquipmentStateRepository(EquipmentContext equipmentContext)
        {
            _equipmentContext = equipmentContext;
        }
        public EquipmentState Add(EquipmentState equipment)
            {
                {
                    //Verificação se nome está vazio
                    if (string.IsNullOrEmpty(equipment.Name))
                    {
                        throw new Exception("A inserção do Estado Atual do Equipamento é obrigatória!");
                    }
                    if (string.IsNullOrEmpty(equipment.Color))
                    {
                        throw new Exception("A inserção de uma cor é obrigatória!");
                    }
                    //Verifica a já existência de um equipament com o Id provido.
                    if (_equipmentContext.EquipmentState.Any(findEquipmentState => findEquipmentState.Id == equipment.Id)) //Se qualquer elemento for = equipment.Id retorna true 
                    {
                        //Caso já exista, retorna o erro
                        throw new Exception("O ID que você tentou já foi registrado para o Estado de um equipamento, tente outro!");
                    }
                    else
                    {
                        try
                        {
                            // Adiciona o objeto ao contexto
                            _equipmentContext.EquipmentState.Add(equipment);
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
            }

        public EquipmentState Edit(EquipmentState equipment)
        {
            if (equipment == null)
            {
                throw new ArgumentNullException(nameof(equipment), "O valor recebido é nulo.");
            }
            // Busca o Equipamento a ser atualizado no Banco
            EquipmentState equipmentToUpdate = _equipmentContext.EquipmentState.Find(equipment.Id);
            if (equipmentToUpdate == null) throw new Exception($"Não há itens com o Id {equipment.Id}");
            else
            {
                //Checagem sem os campos foram preenchidos, caso não, mantém-se o valor anterior.
                if (equipment.Name != null)
                {
                    equipmentToUpdate.Name = equipment.Name;
                }
                if (equipment.Color != null)
                {
                    equipmentToUpdate.Color = equipment.Color;
                }
                //Atualiza o Nome caso os campo tenha sido preenchidos.
                _equipmentContext.EquipmentState.Update(equipmentToUpdate);
                //Salva no BD
                _equipmentContext.SaveChanges();
                return equipmentToUpdate;
            }
        }
                //Listagem para View
        public EquipmentState Remove (Guid id)
        {
            //Busca do elemento a ser excluido pelo id.
            var equipment = _equipmentContext.EquipmentState.Find(id);
            //Verifica a existência do estado do equipamento
            if (equipment != null)
            {
                try
                {
                    //Remove o objeto
                    _equipmentContext.EquipmentState.Remove(equipment);
                    //Salva
                    _equipmentContext.SaveChanges();
                    return equipment;
                }
                catch (DbUpdateException)
                {
                    throw new Exception($"O Estado do Equipamento está sendo utilizado, verifique no referente ao : Valor por Hora ou o Histórico de estados por equipamento." +
                        $"É necessário que a deleção seja feita lá primeiro");
                }

            }
            else
            {
                //Caso não encontre o elemento especificado.
                throw new Exception("O modelo de equipamento com o ID informado não foi encontrado");
            }
        }
        public List<EquipmentState> GetAllEquipmentState()
                {
                    return _equipmentContext.EquipmentState.ToList();
                }
      
        }
    }
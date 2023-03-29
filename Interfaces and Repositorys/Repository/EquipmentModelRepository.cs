using API_CRUD_Test.Data;
using API_CRUD_Test.Models;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.EntityFrameworkCore;
using System.Data;

namespace API_CRUD_Test.Repository
{
    public class EquipmentModelRepository : IEquipmentModel
    {
        private readonly EquipmentContext _equipmentContext;
        public EquipmentModelRepository(EquipmentContext equipmentContext)
        {
            _equipmentContext = equipmentContext;
        }
        //  Listagem para o View
        public List<EquipmentModel> GetAllEquipmentModel()
        {
            return _equipmentContext.EquipmentModel.ToList();

        }
        // Adiciona modelo
        public EquipmentModel Add(EquipmentModel equipment)
        {

            //Verificação se nome está vazio
            if (string.IsNullOrEmpty(equipment.Name))
            {
                throw new Exception("A inserção do nome é obrigatória!");
            } 

            //Verifica a já existência de um equipament com o Id provido.
            if (_equipmentContext.EquipmentModel.Any(findEquipmentModel => findEquipmentModel.Id == equipment.Id)) //Se qualquer elemento for = equipment.Id retorna true 
            {
                //Caso já exista, retorna o erro
                throw new Exception("Já existe um modelo de equipamento com o ID informado, tente outro!");
            }
            else
            { 
                try
                {
                    // Adiciona o objeto ao contexto
                    _equipmentContext.EquipmentModel.Add(equipment);
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
        //Remove Modelo
        public EquipmentModel Remove(Guid id)
        {
            //Busca do elemento a ser excluido pelo id.
            var equipment = _equipmentContext.EquipmentModel.Find(id);
            //Verifica a existência do modelo de equipamento
                if (equipment != null)
                {
                try
                {
                    //Remove o objeto
                    _equipmentContext.EquipmentModel.Remove(equipment);
                    //Salva
                    _equipmentContext.SaveChanges();
                    return equipment;
                }
                catch(DbUpdateException)
                {
                    throw new Exception($"O Modelo do Equipamento está sendo utilizado, verifique no referente ao : Valor por Hora ou nos Equipamentos." +
                   $"É necessário que a deleção seja feita lá primeiro");
                }

                }
                else
                {
                    //Caso não encontre o elemento especificado.
                    throw new Exception("O modelo de equipamento com o ID informado não foi encontrado");
                }
        }
        public EquipmentModel Edit(EquipmentModel equipment)
        {
            if (equipment == null)
            {
                throw new ArgumentNullException(nameof(equipment), "O modelo do equipamento recebido é nulo.");
            }
            // Busca o Equipamento a ser atualizado no Banco
            EquipmentModel equipmentToUpdate = _equipmentContext.EquipmentModel.Find(equipment.Id);
            if (equipmentToUpdate == null) throw new Exception($"Não há itens com o Id {equipment.Id}");
            else
            {
                //Checo se os campos foram devidamente preenchidos
                if (equipment.Name == null)
                {
                    throw new Exception("Por favor, insira um nome");
                }
                else
                {
                    //Atualiza o Nome caso os campo tenha sido preenchidos.
                    equipmentToUpdate.Name = equipment.Name;
                    _equipmentContext.EquipmentModel.Update(equipmentToUpdate);
                    //Salva no BD
                    _equipmentContext.SaveChanges();
                    return equipmentToUpdate;
                }

            }

        }
    }
}

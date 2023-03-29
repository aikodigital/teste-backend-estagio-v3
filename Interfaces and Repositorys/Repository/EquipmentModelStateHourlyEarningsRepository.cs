using API_CRUD_Test.Data;
using API_CRUD_Test.Models;
using API_CRUD_Test.Repository.Interfaces;
using Microsoft.EntityFrameworkCore;
using System.Data;

namespace API_CRUD_Test.Repository
{
    public class EquipmentModelStateHourlyEarningsRepository : IEquipmentModelStateHourlyEarnings
    {
        private readonly EquipmentContext _equipmentContext;
        public EquipmentModelStateHourlyEarningsRepository(EquipmentContext equipmentContext)
        {
            _equipmentContext = equipmentContext;
        }
        
        //Adicionar
        public EquipmentModelStateHourlyEarnings Add(EquipmentModelStateHourlyEarnings equipment)
        {

                //Verificação se o Modelo e o Estado estão preenchidos
                if (equipment.EquipmentModelId == Guid.Empty || equipment.EquipmentStateId == Guid.Empty)
                {
                    throw new Exception("A inserção do ID do estado e do modelo do equipamento é obrigatória!");
                }

                //Verifica a já existência de um modelo e estado com os ids providos.
                var model = _equipmentContext.EquipmentModel.FirstOrDefault(m => m.Id == equipment.EquipmentModelId);
                var state = _equipmentContext.EquipmentState.FirstOrDefault(s => s.Id == equipment.EquipmentStateId);

                if (model == null || state == null)
                {
                    throw new Exception("O modelo ou o estado com o ID inserido não foi encontrado no nosso banco de dados");
                }
                else
                {
                    try
                    {
                        // Adiciona o objeto ao contexto
                        _equipmentContext.EquipmentModelStateHourlyEarnings.Add(equipment);
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
        public EquipmentModelStateHourlyEarnings Edit(EquipmentModelStateHourlyEarnings equipment)
        {
            if (equipment == null)
            {
                throw new ArgumentNullException("O valor recebido é nulo.");
            }
            // Busca o Equipamento a ser atualizado no Banco
            EquipmentModelStateHourlyEarnings equipmentToUpdate = _equipmentContext.EquipmentModelStateHourlyEarnings.Find(equipment.EquipmentStateId, equipment.EquipmentModelId);
            if (equipmentToUpdate == null) throw new Exception($"Não há itens do modelo com Id: {equipment.EquipmentModelId} no estado com Id: {equipment.EquipmentStateId}");
            else
            {
                //Checagem sem os campos foram preenchidos, caso não, mantém-se o valor anterior.
                if (equipment.Value != float.NaN)
                {
                    equipmentToUpdate.Value = equipment.Value;
                }
                if (equipment.EquipmentStateId != Guid.Empty)
                {
                    equipmentToUpdate.EquipmentStateId = equipment.EquipmentStateId;
                }
                //Atualiza o Nome caso os campo tenha sido preenchidos.
                _equipmentContext.EquipmentModelStateHourlyEarnings.Update(equipmentToUpdate);
                //Salva no BD
                _equipmentContext.SaveChanges();
                return equipmentToUpdate;
            }
        }
        public List<EquipmentModelStateHourlyEarnings> GetAllEquipmentModelStateHourlyEarnings()
            {
                return _equipmentContext.EquipmentModelStateHourlyEarnings.ToList();
            }
        public EquipmentModelStateHourlyEarnings Remove(Guid modelId, Guid stateId)
        {
            //Busca do elemento a ser excluido pelo id.
            var equipment = _equipmentContext.EquipmentModelStateHourlyEarnings.FirstOrDefault(e => e.EquipmentModelId == modelId && e.EquipmentStateId == stateId);

            //Verifica a existência do modelo de equipamento
            if (equipment != null)
            {
                try
                {
                    //Remove o objeto
                    _equipmentContext.EquipmentModelStateHourlyEarnings.Remove(equipment);
                    //Salva
                    _equipmentContext.SaveChanges();
                    return equipment;
                }
                catch (DbUpdateException)
                {
                    throw new Exception($"O Modelo do Equipamento está sendo utilizado, verifique no referente ao : Valor por Hora ou nos Equipamentos." +
                   $"É necessário que a deleção seja feita lá primeiro");
                }

            }
            else
            {
                //Caso não encontre o elemento especificado.
                throw new Exception("ID de Estado ou de Modelo não foram encontrados no banco de dados!");
            }
        }
    }
}

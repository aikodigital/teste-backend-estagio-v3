using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Models;
using Microsoft.Extensions.Configuration;
using Npgsql;

namespace EquipmentInfra.Repositories
{
    public class EquipmentStateHistoryRepository : IEquipmentStateHistoryRepository
    {
        private readonly DbequipamentoContext _dbequipamentoContext;
        private readonly string _connectionstring;

        public EquipmentStateHistoryRepository(DbequipamentoContext dbequipamentoContext, IConfiguration configuracao)
        {
            _dbequipamentoContext = dbequipamentoContext;
            _connectionstring = configuracao.GetConnectionString("DefaultConnection");
        }

        public List<EquipmentStateHistory> GetEquipmentStateHistory()
        {
            return (from E in _dbequipamentoContext.EquipmentStateHistories
                    select E).ToList();
        }

        public string AddNewEquipmentStateHistory(EquipmentStateHistory newEquipment)
        {
            using (var connection = new NpgsqlConnection(_connectionstring))
            {
                connection.Open();
                using var cmd = new NpgsqlCommand();
                cmd.Connection = connection;
                cmd.CommandText = $@"INSERT INTO operation.equipment_state_history(equipment_id, date, equipment_state_id)
                                     VALUES ('{newEquipment.EquipmentId}', '{newEquipment.Date.ToString("yyyy-MM-dd HH:mm:ss")}', '{newEquipment.EquipmentStateId}');";

                var sim = cmd.ExecuteNonQuery();
            }

            return "Histórico de estados de um equipamento adicionado com sucesso.";
        }

        public string UpdateEquipmentStateHistory(EquipmentStateHistory storedEquipment, Guid equipmentId, Guid equipmentStateId, DateTime date)
        {
            using (var connection = new NpgsqlConnection(_connectionstring))
            {
                connection.Open();
                using var cmd = new NpgsqlCommand();
                cmd.Connection = connection;

                cmd.CommandText = $@"UPDATE operation.equipment_state_history
                                     SET equipment_id='{equipmentId}', equipment_state_id='{equipmentStateId}', date='{date.ToString("yyyy-MM-dd HH:mm:ss")}'
                                     WHERE equipment_id='{storedEquipment.EquipmentId}'  
                                        AND equipment_state_id='{storedEquipment.EquipmentStateId}' 
                                        AND date= '{storedEquipment.Date.ToString("yyyy-MM-dd HH:mm:ss")}';";


                var sim = cmd.ExecuteNonQuery();
            }

            return "Histórico de estados de um equipamento atualizado com sucesso.";
        }

        public string DeleteEquipmentStateHistory(EquipmentStateHistory storedEquipment)
        {

            using (var connection = new NpgsqlConnection(_connectionstring))
            {
                connection.Open();
                using var cmd = new NpgsqlCommand();
                cmd.Connection = connection;

                cmd.CommandText = $@"DELETE FROM operation.equipment_state_history 
                                     WHERE equipment_id='{storedEquipment.EquipmentId}'  
                                        AND equipment_state_id='{storedEquipment.EquipmentStateId}' 
                                        AND date= '{storedEquipment.Date.ToString("yyyy-MM-dd HH:mm:ss")}';";

                var sim = cmd.ExecuteNonQuery();
            }

            return "Histórico de estados de um equipamento excluído com sucesso.";
        }
    }
}

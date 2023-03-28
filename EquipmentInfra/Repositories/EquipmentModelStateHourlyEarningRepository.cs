using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Npgsql;

namespace EquipmentInfra.Repositories
{
    public class EquipmentModelStateHourlyEarningRepository : IEquipmentModelStateHourlyEarningRepository
    {
        private readonly DbequipamentoContext _dbequipamentoContext;
        private readonly string _connectionstring;

        public EquipmentModelStateHourlyEarningRepository(DbequipamentoContext dbequipamentoContext, IConfiguration configuracao)
        {
            _dbequipamentoContext = dbequipamentoContext;
            _connectionstring = configuracao.GetConnectionString("DefaultConnection");
        }

        public List<EquipmentModelStateHourlyEarning> GetEquipmentModelStateHourlyEarning()
        {
            return (from E in _dbequipamentoContext.EquipmentModelStateHourlyEarnings
                    select E).ToList();
        }

        public string AddNewEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarning newEquipment)
        {
            using (var connection = new NpgsqlConnection(_connectionstring))
            {
                connection.Open();
                using var cmd = new NpgsqlCommand();
                cmd.Connection = connection;

                cmd.CommandText = $@"INSERT INTO operation.equipment_model_state_hourly_earnings(equipment_model_id, equipment_state_id, value)
                                     VALUES ('{newEquipment.EquipmentModelId}', '{newEquipment.EquipmentStateId}', {newEquipment.Value});";


                var sim = cmd.ExecuteNonQuery();
            }

            return "Ganho por hora e por estado adicionado com sucesso.";
        }

        public string UpdateEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarning storedEquipment, float value, Guid equipmentModelId, Guid equipmentStateId)
        {

            using (var connection = new NpgsqlConnection(_connectionstring))
            {
                connection.Open();
                using var cmd = new NpgsqlCommand();
                cmd.Connection = connection;

                cmd.CommandText = $@"UPDATE operation.equipment_model_state_hourly_earnings 
                                     SET value={value}, equipment_model_id ='{equipmentModelId}', equipment_state_id ='{equipmentStateId}' 
                                     WHERE equipment_model_id='{storedEquipment.EquipmentModelId}'  
                                        AND equipment_state_id='{storedEquipment.EquipmentStateId}' 
                                        AND value={storedEquipment.Value};";

                var sim = cmd.ExecuteNonQuery();
            }

            return "Ganho por hora e por estado atualizado com sucesso.";
        }

        public string DeleteEquipmentModelStateHourlyEarning(EquipmentModelStateHourlyEarning storedEquipment)
        {

            using (var connection = new NpgsqlConnection(_connectionstring))
            {
                connection.Open();
                using var cmd = new NpgsqlCommand();
                cmd.Connection = connection;

                cmd.CommandText = $@"DELETE FROM operation.equipment_model_state_hourly_earnings 
                                     WHERE equipment_model_id='{storedEquipment.EquipmentModelId}'
                                        AND equipment_state_id='{storedEquipment.EquipmentStateId}' 
                                        AND value={storedEquipment.Value};";

                var sim = cmd.ExecuteNonQuery();
            }

            return "Ganho por hora e por estado excluído com sucesso.";
        }
    }
}

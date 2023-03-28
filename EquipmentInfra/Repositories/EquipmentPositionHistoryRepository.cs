using EquipmentDomain.Interfaces.Repositories;
using EquipmentDomain.Models;
using Microsoft.Extensions.Configuration;
using Npgsql;
using System.Globalization;


namespace EquipmentInfra.Repositories
{
    public class EquipmentPositionHistoryRepository : IEquipmentPositionHistoryRepository
    {
        private readonly DbequipamentoContext _dbequipamentoContext;
        private readonly string _connectionstring;
        public EquipmentPositionHistoryRepository(DbequipamentoContext dbequipamentoContext, IConfiguration configuracao)
        {
            _dbequipamentoContext = dbequipamentoContext;
            _connectionstring = configuracao.GetConnectionString("DefaultConnection");
        }

        public List<EquipmentPositionHistory> GetEquipmentPositionHistory()
        {
            return (from E in _dbequipamentoContext.EquipmentPositionHistories
                    select E).ToList();
        }

        public string AddNewEquipmentPositionHistory(EquipmentPositionHistory newEquipment)
        {
            using (var connection = new NpgsqlConnection(_connectionstring))
            {
                connection.Open();
                using var cmd = new NpgsqlCommand();
                cmd.Connection = connection;

                cmd.CommandText = $@"INSERT INTO operation.equipment_position_history(equipment_id, date, lat, lon)
                                     VALUES ('{newEquipment.EquipmentId}', '{DateTime.Now}', {newEquipment.Lat.ToString().Replace(",", ".")}, {newEquipment.Lon.ToString().Replace(",", ".")});";



                var sim = cmd.ExecuteNonQuery();
            }

            return "Histórico de posições de um equipamento adicionado com sucesso.";
        }

        public string UpdateEquipmentPositionHistory(EquipmentPositionHistory storedEquipment, float lat, float lon, Guid equipmentId)
        {
            using (var connection = new NpgsqlConnection(_connectionstring))
            {
                connection.Open();
                using var cmd = new NpgsqlCommand();
                cmd.Connection = connection;

                cmd.CommandText = $@"UPDATE operation.equipment_position_history
                                     SET lat={lat.ToString().Replace(",", ".")}, lon={lon.ToString().Replace(",", ".")}, equipment_id='{equipmentId}'
                                     WHERE equipment_id='{storedEquipment.EquipmentId}'  
                                        AND date='{storedEquipment.Date.ToString("yyyy-MM-dd HH:mm:ss")}' 
                                        AND lat= '{storedEquipment.Lat.ToString().Replace(",", ".")}'
                                        AND lon= '{storedEquipment.Lon.ToString().Replace(",", ".")}';";


                var sim = cmd.ExecuteNonQuery();
            }

            return "Histórico de posições de um equipamento atualizado com sucesso.";
        }

        public string DeleteEquipmentPositionHistory(EquipmentPositionHistory storedEquipment)
        {

            using (var connection = new NpgsqlConnection(_connectionstring))
            {
                connection.Open();
                using var cmd = new NpgsqlCommand();
                cmd.Connection = connection;

                cmd.CommandText = $@"DELETE FROM operation.equipment_position_history 
                                     WHERE equipment_id='{storedEquipment.EquipmentId}'  
                                        AND date='{storedEquipment.Date.ToString("yyyy-MM-dd HH:mm:ss")}' 
                                        AND lat= '{storedEquipment.Lat.ToString().Replace(",", ".")}'
                                        AND lon= '{storedEquipment.Lon.ToString().Replace(",", ".")}';";

                var sim = cmd.ExecuteNonQuery();
            }

            return "Histórico de posições de um equipamento excluído com sucesso.";
        }
    }
}

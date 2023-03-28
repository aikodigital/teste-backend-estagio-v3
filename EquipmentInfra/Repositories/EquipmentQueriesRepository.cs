using Dapper;
using EquipmentDomain.DTOs;
using EquipmentDomain.Interfaces.Repositories;
using Microsoft.Extensions.Configuration;
using Npgsql;

namespace EquipmentInfra.Repositories
{
    public class EquipmentQueriesRepository : IEquipmentQueriesRespository
    {
        private readonly string _connectionstring;

        public EquipmentQueriesRepository(IConfiguration configuracao)
        {
            _connectionstring = configuracao.GetConnectionString("DefaultConnection");
        }

        public IEnumerable<EquipmentActualPositionResponse> GetActualPosition()
        {
            using (NpgsqlConnection connectionString = new(_connectionstring))
            {
                string sql = @"SELECT DISTINCT ON(e.name) E.name , lat, lon
                                FROM operation.equipment_position_history AS EPH
                                JOIN operation.equipment AS E ON E.id = EPH.equipment_id
                                ORDER BY E.name, EPH.date DESC;";

                return connectionString.Query<EquipmentActualPositionResponse>(sql);
            }
        }

        public IEnumerable<EquipmentActualStateResponse> GetActualState()
        {
            using (NpgsqlConnection connectionString = new(_connectionstring))
            {
                string sql = @"SELECT DISTINCT ON(E.name) E.name AS Name, ES.name AS State
                                FROM operation.equipment AS E 
                                JOIN operation.equipment_state_history AS ESH ON E.id = ESH.equipment_id
                                JOIN operation.equipment_state AS ES ON ESH.equipment_state_id = ES.id 
                                ORDER BY E.name, ESH.date desc;";

                return connectionString.Query<EquipmentActualStateResponse>(sql);
            }
        }
    }
}

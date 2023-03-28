using Dapper;
using EquipmentDomain.DTOs;
using Microsoft.AspNetCore.Mvc;
using Npgsql;
using Swashbuckle.AspNetCore.Annotations;

namespace EquipmentApi.Controllers
{
    [Route("api/equipment-actual-position")]
    [ApiController]
    public class EquipmentActualPosition : ControllerBase
    {
        private readonly string _connectionstring;

        public EquipmentActualPosition(IConfiguration configuracao)
        {
            _connectionstring = configuracao.GetConnectionString("DefaultConnection");
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(IEnumerable<EquipmentActualPositionResponse>))]
        public IEnumerable<EquipmentActualPositionResponse> GetEquipment()
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
    }
}

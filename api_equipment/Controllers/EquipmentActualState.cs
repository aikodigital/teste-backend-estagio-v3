using EquipmentDomain.DTOs;
using Microsoft.AspNetCore.Mvc;
using Npgsql;
using Dapper;
using Swashbuckle.AspNetCore.Annotations;

namespace EquipmentApi.Controllers
{
    [Route("api/equipment-actual-state")]
    [ApiController]
    public class EquipmentActualState : ControllerBase
    {
        private readonly string _connectionstring;

        public EquipmentActualState(IConfiguration configuracao)
        {
            _connectionstring = configuracao.GetConnectionString("DefaultConnection");
        }

        [HttpGet]
        [SwaggerResponse(StatusCodes.Status200OK, "", typeof(IEnumerable<EquipmentActualStateResponse>))]
        public IEnumerable<EquipmentActualStateResponse> GetEquipment()
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

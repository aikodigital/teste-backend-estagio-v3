using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using teste_backend_estagio_v3.Models;

namespace teste_backend_estagio_v3.Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class EquipmentController : ControllerBase
	{
		[HttpGet]
		public ActionResult<equipment> BuscarTodosEquipamentos()
		{
			return Ok();
		}
	}
}

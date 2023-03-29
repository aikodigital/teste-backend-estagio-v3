using Microsoft.EntityFrameworkCore;
using teste_backend_estagio_v3.Models;

namespace teste_backend_estagio_v3.Data
{
	public class teste_backend_estagioDBContext : DbContext
	{
		public teste_backend_estagioDBContext(DbContextOptions<teste_backend_estagioDBContext> options)
			: base(options) 
		{

		}

		public DbSet<equipment>? Equipamentos { get; set; }

	}
}

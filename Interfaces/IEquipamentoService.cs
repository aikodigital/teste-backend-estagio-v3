using Prova.Repositorio.Modelos;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Prova1.Servico.Interfaces
{
    public interface IEquipamentoService
    {
        public Task AddEquipamentoAsync(Equipamento equipamento);
        public Task RemoverEquipamentoAsync(int id);
        public Task<Equipamento> LerEquipamentoAsync(int id);
        public Task<Equipamento> EditarEquipamentoAsync(Equipamento equipamento);
    }
}

using Prova.Repositorio.Modelos;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Prova.Repositorio.Interface
{
    public interface IEquipamentoRepository
    {
        public Task AddEquipamentoAsync(Equipamento equipamento);
        public Task RemoverEquipamentoAsync(int id);
        public Task<Equipamento> LerEquipamentoAsync(int id);
        public Task<Equipamento> EditarEquipamentoAsync(Equipamento equipamento);
    }
}

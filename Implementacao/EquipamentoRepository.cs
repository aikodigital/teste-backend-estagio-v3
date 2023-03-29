using Prova.Repositorio.Interface;
using Prova.Repositorio.Modelos;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Prova.Repositorio.Implementacao
{
    public class EquipamentoRepository : IEquipamentoRepository
    {
        
        public Task<Equipamento> EditarEquipamentoAsync(Equipamento equipamento)
        {
            throw new NotImplementedException();
        }

        public Task<Equipamento> LerEquipamentoAsync(int id)
        {
            throw new NotImplementedException();
        }

        public Task RemoverEquipamentoAsync(int id)
        {
            throw new NotImplementedException();
        }

        Task IEquipamentoRepository.AddEquipamentoAsync(Equipamento equipamento)
        {
            throw new NotImplementedException();
        }
    }
}

using TesteTecnico.Application.Application.Common.Interfaces.GenericRepository;
using TesteTecnico.Application.Infrastructure.Persistence.DataContext;

namespace TesteTecnico.Application.Infrastructure.Persistence;

public class GenericRepository<T> : IGenericRepository<T> where T : class
{
    private readonly AppDbContext _dbContext;
    private readonly DbSet<T> _entity;

    public GenericRepository(AppDbContext dbContext)
    {
        _dbContext = dbContext;
        _entity = dbContext.Set<T>();
    }

    public void Add(T entity)
    {
        _entity.Add(entity);
    }

    public void Delete(T entity)
    {
        _entity.Remove(entity);
    }

    public async Task<IEnumerable<T>> GetAllAsync()
    {
        return await _entity.AsNoTracking().ToListAsync();
    }

    public async Task<T?> GetByIdAsync(Guid id)
    {
        return await _entity.FindAsync(id);
    }

    public void Update(T entity)
    {
        _entity.Update(entity);
    }

    public async Task CommitAsync()
    {
        await _dbContext.SaveChangesAsync();
    }
}
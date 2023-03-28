using TesteTecnico.Application.Application.Middlewares;

namespace TesteTecnico.Application.Application.Extensions;

public static class ExceptionMiddlewareExtensions
{
    public static void ConfigureExceptionHandler(this IApplicationBuilder app)
    {
        app.UseMiddleware<ErrorHandlerMiddleware>();
    }
}
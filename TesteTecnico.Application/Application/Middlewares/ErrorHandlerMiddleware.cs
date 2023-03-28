using System.Net;
using System.Text.Json;
using TesteTecnico.Application.Application.Common.Exceptions;

namespace TesteTecnico.Application.Application.Middlewares;

public class ErrorHandlerMiddleware
{
    private readonly RequestDelegate _next;
    public ErrorHandlerMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        try
        {
            await _next(context);
        }
        catch (Exception exception)
        {
            await HandleExceptionAsync(context, exception);
        }
    }

    public async Task HandleExceptionAsync(HttpContext context, Exception exception)
    {
        HttpStatusCode statusCode;
        string message;
        switch (exception)
        {
            case BadRequestException:
                statusCode = HttpStatusCode.BadRequest;
                message = exception.Message;
                break;
            case ConflictException:
                statusCode = HttpStatusCode.Conflict;
                message = exception.Message;
                break;
            case NotFoundException:
                statusCode = HttpStatusCode.NotFound;
                message = exception.Message;
                break;
            default:
                statusCode = HttpStatusCode.InternalServerError;
                message = "Erro interno no sistema, tente novamente mais tarde.";
                break;
        }
        Console.WriteLine(exception);

        context.Response.StatusCode = (int)statusCode;
        await context.Response.WriteAsync(JsonSerializer.Serialize(new { statusCode, message }));
    }
}
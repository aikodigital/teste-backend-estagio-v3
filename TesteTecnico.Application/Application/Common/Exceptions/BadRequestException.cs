namespace TesteTecnico.Application.Application.Common.Exceptions;

public class BadRequestException : Exception
{
    public BadRequestException(string message) : base(message) { }

    public BadRequestException() : base() { }

    public BadRequestException(string? message, Exception? innerException) : base(message, innerException) { }
}
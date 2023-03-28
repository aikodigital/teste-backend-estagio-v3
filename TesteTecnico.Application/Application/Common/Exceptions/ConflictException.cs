namespace TesteTecnico.Application.Application.Common.Exceptions;

public class ConflictException : Exception
{
    public ConflictException(string message) : base(message) { }

    public ConflictException() : base() { }

    public ConflictException(string? message, Exception? innerException) : base(message, innerException) { }
}
package logger;

public record LogMessage(LogLevel level, String message, long timestamp) {}

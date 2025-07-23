package logger.formatter;

import logger.LogMessage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class JsonLogFormatter implements LogFormatter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public String format(LogMessage logMessage) {
        String formattedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(logMessage.timestamp()), ZoneId.systemDefault()).format(FORMATTER);
        return String.format("""
                {
                    "timestamp": "%s",
                    "level": "%s",
                    "message": "%s"
                }""", formattedDate, logMessage.level(), logMessage.message());
    }
}

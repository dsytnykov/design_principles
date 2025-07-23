package logger.formatter;

import logger.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}

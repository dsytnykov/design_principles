package logger.appender;

import logger.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}

package logger.handler;

import logger.LogLevel;

public class ErrorLogHandler extends LogHandler {

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }
}

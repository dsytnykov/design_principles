package logger.handler;

import logger.LogLevel;

public class WarnLogHandler extends LogHandler{

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.WARN;
    }
}

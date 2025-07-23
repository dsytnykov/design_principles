package logger;

import logger.appender.LogAppender;
import logger.handler.DebugLogHandler;
import logger.handler.ErrorLogHandler;
import logger.handler.LogHandler;
import logger.handler.WarnLogHandler;

public class LogHandlerConfiguration {
    private static final LogHandler debug = new DebugLogHandler();
    private static final LogHandler warn = new WarnLogHandler();
    private static final LogHandler error = new ErrorLogHandler();

    public static LogHandler build() {
        debug.setNext(warn);
        warn.setNext(error);
        return debug;
    }

    public static void addAppenderForLevel(LogAppender appender, LogLevel level) {
        switch(level) {
            case DEBUG -> debug.subscribe(appender);
            case WARN -> warn.subscribe(appender);
            case ERROR -> error.subscribe(appender);
        }
    }
}

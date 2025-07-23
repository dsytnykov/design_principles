package logger;

import logger.appender.ConsoleLogAppender;
import logger.appender.FileLogAppender;
import logger.formatter.JsonLogFormatter;
import logger.formatter.TextLogFormatter;
import logger.handler.LogHandler;

public class Logger {
    private final static Logger  INSTANCE = new Logger();
    private final LogHandler rootHandler;

    private Logger() {
        rootHandler = LogHandlerConfiguration.build();
        LogHandlerConfiguration.addAppenderForLevel(new ConsoleLogAppender(new TextLogFormatter()), LogLevel.DEBUG);

        LogHandlerConfiguration.addAppenderForLevel(new ConsoleLogAppender(new TextLogFormatter()), LogLevel.WARN);

        LogHandlerConfiguration.addAppenderForLevel(new ConsoleLogAppender(new TextLogFormatter()), LogLevel.ERROR);
        LogHandlerConfiguration.addAppenderForLevel(new FileLogAppender(new JsonLogFormatter(), "error.log"), LogLevel.ERROR);
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void log(LogLevel level, String message) {
        LogMessage logMessage = new LogMessage(level, message, System.currentTimeMillis());
        rootHandler.handle(logMessage);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message){
        log(LogLevel.ERROR, message);
    }

}
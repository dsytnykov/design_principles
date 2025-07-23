package logger.handler;

import logger.LogLevel;
import logger.LogMessage;
import logger.appender.LogAppender;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {
    private LogHandler next;
    private final List<LogAppender> appenders = new CopyOnWriteArrayList<>();

    public void setNext(LogHandler next) {
        this.next = next;
    }

    public void subscribe(LogAppender appender) {
        appenders.add(appender);
    }

    public void notifyAllAppenders(LogMessage logMessage) {
        for (LogAppender appender : appenders) {
            appender.append(logMessage);
        }
    }

    public void handle(LogMessage logMessage){
        if (canHandle(logMessage.level())) {
            notifyAllAppenders(logMessage);
        } else {
            if (next != null) {
                next.handle(logMessage);
            }
        }
    }

    protected abstract boolean canHandle(LogLevel level);
}

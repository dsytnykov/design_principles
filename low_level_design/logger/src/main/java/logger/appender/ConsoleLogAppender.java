package logger.appender;

import logger.LogMessage;
import logger.formatter.LogFormatter;

public class ConsoleLogAppender implements LogAppender {
    private LogFormatter formatter;

    public ConsoleLogAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(formatter.format(logMessage));
    }
}

package logger.appender;

import logger.LogMessage;
import logger.formatter.LogFormatter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogAppender implements LogAppender {
    private final LogFormatter formatter;
    private final String filePath;

    public FileLogAppender(LogFormatter formatter, String filePath) {
        this.formatter = formatter;
        this.filePath = filePath;
    }

    @Override
    public synchronized void append(LogMessage logMessage) {
        String formattedMessage = formatter.format(logMessage);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(formattedMessage);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

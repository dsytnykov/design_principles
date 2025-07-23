import logger.Logger;

public class LoggerDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.debug("debug message");
        logger.error("Error message");
    }
}

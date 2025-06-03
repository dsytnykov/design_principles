public class Demo {
    public static void main(String[] args) {
        Handler spamHandler = new SpamHandler();
        Handler fanHandler = new FanHandler();
        Handler complainHandler = new ComplainHandler();
        Handler supportHandler = new SupportHandler();

        spamHandler.setHandler(fanHandler);
        fanHandler.setHandler(complainHandler);
        complainHandler.setHandler(supportHandler);

        spamHandler.handleRequest(Request.SPAM);

        System.out.println("---------------------------");

        spamHandler.handleRequest(Request.SUPPORT);

        System.out.println("---------------------------");

        try {
            spamHandler.setHandler(spamHandler);
        } catch (IllegalArgumentException e) {
            System.out.println("The error was detected: " + e.getMessage());
        }


    }
}

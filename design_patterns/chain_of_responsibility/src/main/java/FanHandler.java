class FanHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if(request == Request.FAN) {
            System.out.println("FanHandler handled request");
            return;
        }
        System.out.println("It was tried in FanHandler but didn't meet the condition.");
        next.handleRequest(request);
    }
}

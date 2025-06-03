class ComplainHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if(request == Request.COMPLAIN) {
            System.out.println("Complain handled by ComplainHandler");
            return;
        }
        System.out.println("It was tried in ComplainHandler but didn't meet the condition.");
        next.handleRequest(request);
    }
}

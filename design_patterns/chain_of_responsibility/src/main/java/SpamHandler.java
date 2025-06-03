class SpamHandler extends Handler{
    @Override
    public void handleRequest(Request request) {
        if(request.equals(Request.SPAM)) {
            System.out.println("Spam handled by SpamHandler");
            return;
        }
        System.out.println("It was tried in SpamHandler but didn't meet the condition.");
        next.handleRequest(request);
    }
}

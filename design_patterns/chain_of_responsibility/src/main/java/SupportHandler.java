class SupportHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if(request == Request.SUPPORT) {
            System.out.println("SupportHandler handled request");
            return;
        }
        System.out.println("It was the last handler. It means that the request was not handled.");
    }
}

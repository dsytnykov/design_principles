abstract class Handler {
    protected Handler next;

    public void setHandler(Handler next) {
        if(this == next) {
            throw new IllegalArgumentException("You can't set the same handler");
        }
        this.next = next;
    }

    public abstract void handleRequest(Request request);
}

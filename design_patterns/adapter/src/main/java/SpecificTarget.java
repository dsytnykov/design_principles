public class SpecificTarget implements Target {
    @Override
    public void request() {
        System.out.println("Request from SpecificTarget");
    }
}

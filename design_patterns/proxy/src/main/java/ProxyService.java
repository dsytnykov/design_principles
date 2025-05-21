public class ProxyService implements Service {
    private final Service service;

    public ProxyService(Service service) {
        this.service = service;
    }

    @Override
    public void request() {
        System.out.println("request from proxy service before real service");
        service.request();
        System.out.println("request from proxy service after real service");
    }
}

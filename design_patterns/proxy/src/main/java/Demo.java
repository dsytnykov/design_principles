public class Demo {
    public static void main(String[] args) {
        Service service = new RealService();
        ProxyService proxyService = new ProxyService(service);
        proxyService.request();
    }
}

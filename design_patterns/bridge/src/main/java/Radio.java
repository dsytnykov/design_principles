public class Radio implements Device {
    @Override
    public void turnOn() {
        System.out.println("Radio turn on");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio turn off");
    }

    @Override
    public void setChannel(int channel) {
        System.out.println("Radio set channel " + channel);
    }
}

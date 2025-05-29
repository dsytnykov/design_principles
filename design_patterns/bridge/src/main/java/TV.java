public class TV implements Device {
    @Override
    public void turnOn() {
        System.out.println("TV turn on");
    }

    @Override
    public void turnOff() {
        System.out.println("TV turn off");
    }

    @Override
    public void setChannel(int channel) {
        System.out.println("TV set channel " + channel);
    }
}

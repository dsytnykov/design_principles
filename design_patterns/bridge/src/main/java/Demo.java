public class Demo {

    public static void main(String[] args) {
        RemoteControl remoteControlTv = new RemoteControl(new TV());
        remoteControlTv.turnOn();
        remoteControlTv.setChannel(10);
        remoteControlTv.turnOff();

        RemoteControl remoteControlRadio = new RemoteControl(new Radio());
        remoteControlRadio.turnOn();
        remoteControlRadio.setChannel(22);
        remoteControlRadio.turnOff();

    }
}

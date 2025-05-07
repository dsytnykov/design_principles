import command.CeilingFanHighCommand;
import command.CeilingFanOffCommand;
import command.Command;
import command.GarageDoorDownCommand;
import command.GarageDoorUpCommand;
import command.LightOffCommand;
import command.LightOnCommand;
import command.MacroCommand;
import command.StereoOffCommand;
import command.StereoOnWithCdCommand;
import command.TVOffCommand;
import command.TVOnCommand;
import receiver.CeilingFan;
import receiver.Door;
import receiver.Light;
import receiver.Stereo;
import receiver.TV;

public class Demo {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        //Configure receivers
        Light livingRoomLight = new Light("Living room");
        Light kitchenLight = new Light("Kitchen");
        CeilingFan ceilingFan = new CeilingFan("Living room");
        Door garageDoor = new Door("Garage");
        Stereo stereo = new Stereo("Living room");
        TV tv = new TV("Living room");

        //Configure commands
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLigthOff = new LightOffCommand(livingRoomLight);

        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        CeilingFanHighCommand ceilingFanHighCommand = new CeilingFanHighCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

        GarageDoorUpCommand garageDoorUp = new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand garageDoorDown = new GarageDoorDownCommand(garageDoor);

        StereoOnWithCdCommand stereoOnWithCD = new StereoOnWithCdCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);

        TVOnCommand tvOn = new TVOnCommand(tv);
        TVOffCommand tvOff = new TVOffCommand(tv);

        MacroCommand partyOnMacro = new MacroCommand(new Command[]{livingRoomLightOn, stereoOnWithCD, tvOn});
        MacroCommand partyOffMacro = new MacroCommand(new Command[]{livingRoomLigthOff, stereoOff, tvOff});

        //Configure remote control
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLigthOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, ceilingFanHighCommand, ceilingFanOff);
        remoteControl.setCommand(3, stereoOnWithCD, stereoOff);
        remoteControl.setCommand(4, tvOn, tvOff);
        remoteControl.setCommand(5, partyOnMacro, partyOffMacro);
        remoteControl.setCommand(6, garageDoorUp, garageDoorDown);

        System.out.println(remoteControl);

        //Fan part - clicking on buttons
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.undoButtonWasPushed();
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);
        remoteControl.onButtonWasPushed(4);
        remoteControl.offButtonWasPushed(4);
        remoteControl.onButtonWasPushed(5);
        remoteControl.offButtonWasPushed(5);

        System.out.println(remoteControl);
    }
}

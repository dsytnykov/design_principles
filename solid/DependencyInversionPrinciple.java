public class DependencyInversionPrinciple {
}

//WRONG
/*
public class LightBulb {
    public void turnOn() {
        System.out.println("LightBulb on");
    }

    public void turnOff() {
        System.out.println("LightBulb off");
    }
}

public class Switch {
    private LightBulb lightBulb;

    public Switch(LightBulb lightBulb) {
        this.lightBulb = lightBulb;
    }

    public void operate() {
        lightBulb.turnOn();
    }

    public static void main(String[] args) {
        LightBulb lightBulb = new LightBulb();
        Switch lightSwitch = new Switch(lightBulb);
        lightSwitch.operate();
    }
}
 */

//CORRECT
/*
// Switchable.java
public interface Switchable {
    void turnOn();
    void turnOff();
}

// LightBulb.java
public class LightBulb implements Switchable {
    public void turnOn() {
        System.out.println("LightBulb on");
    }

    public void turnOff() {
        System.out.println("LightBulb off");
    }
}

// Switch.java
public class Switch {
    private Switchable device;

    public Switch(Switchable device) {
        this.device = device;
    }

    public void operate() {
        device.turnOn();
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Switchable lightBulb = new LightBulb();
        Switch lightSwitch = new Switch(lightBulb);
        lightSwitch.operate();
    }
}
 */
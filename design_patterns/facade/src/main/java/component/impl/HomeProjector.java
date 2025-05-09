package component.impl;

import component.Projector;

public class HomeProjector implements Projector {
    @Override
    public void on() {
        System.out.println("Projector on");
    }

    @Override
    public void off() {
        System.out.println("Projector off");
    }

    @Override
    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode (16x9)");
    }
}

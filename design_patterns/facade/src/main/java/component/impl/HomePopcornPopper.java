package component.impl;

import component.PopcornPopper;

public class HomePopcornPopper implements PopcornPopper {
    @Override
    public void on() {
        System.out.println("Popcorn popper on");
    }

    @Override
    public void off() {
        System.out.println("Popcorn popper off");
    }

    @Override
    public void pop() {
        System.out.println("Popcorn popper pop");
    }
}

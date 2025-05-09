package component.impl;

import component.Screen;

public class HomeScreen implements Screen {
    @Override
    public void up() {
        System.out.println("Screen up");
    }

    @Override
    public void down() {
        System.out.println("Screen down");
    }
}

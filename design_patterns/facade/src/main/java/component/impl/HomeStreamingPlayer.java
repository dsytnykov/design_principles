package component.impl;

import component.StreamingPlayer;

public class HomeStreamingPlayer implements StreamingPlayer {
    @Override
    public void on() {
        System.out.println("Streaming player is on");
    }

    @Override
    public void off() {
        System.out.println("Streaming player is off");
    }

    @Override
    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    @Override
    public void stop() {
        System.out.println("Stopping movie");
    }
}

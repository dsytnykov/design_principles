package component.impl;

import component.Amplifier;
import component.StreamingPlayer;

public class HomeAmplifier implements Amplifier {
    @Override
    public void on() {
        System.out.println("HomeAmplifier on");
    }

    @Override
    public void off() {
        System.out.println("HomeAmplifier off");
    }

    @Override
    public void setStreamingPlayer(StreamingPlayer player) {
        System.out.println("HomeAmplifier setStreamingPlayer");
    }

    @Override
    public void setSurroundSound() {
        System.out.println("HomeAmplifier setSurroundSound");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("HomeAmplifier setVolume + " + volume);
    }
}

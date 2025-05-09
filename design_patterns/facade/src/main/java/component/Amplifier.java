package component;

public interface Amplifier {
    void on();
    void off();
    void setStreamingPlayer(StreamingPlayer player);
    void setSurroundSound();
    void setVolume(int volume);
}

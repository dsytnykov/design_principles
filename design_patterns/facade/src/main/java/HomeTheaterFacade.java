import component.Amplifier;
import component.PopcornPopper;
import component.Projector;
import component.Screen;
import component.StreamingPlayer;

public class HomeTheaterFacade {
    private final Amplifier amplifier;
    private final StreamingPlayer player;
    private final Projector projector;
    private final Screen screen;
    private final PopcornPopper popper;

    public HomeTheaterFacade(Amplifier amplifier, StreamingPlayer player, Projector projector, Screen screen, PopcornPopper popper) {
        this.amplifier = amplifier;
        this.player = player;
        this.projector = projector;
        this.screen = screen;
        this.popper = popper;
    }


    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amplifier.on();
        amplifier.setStreamingPlayer(player);
        amplifier.setSurroundSound();
        amplifier.setVolume(4);
        player.on();
        player.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        popper.off();
        projector.off();
        screen.up();
        amplifier.off();
        player.stop();
        player.off();
    }
}
